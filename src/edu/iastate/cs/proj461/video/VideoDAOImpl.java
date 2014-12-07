package edu.iastate.cs.proj461.video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class VideoDAOImpl implements VideoDAO{
	
	private SessionFactory sf;

	public VideoDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Video> findVideoByCapturedDateTime(String datetime, boolean searchEntireDay) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//Query query = session.createQuery("from Video where CapturedDateTime>:dateTimeParam");
		final List<Video> results = new LinkedList<Video>();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-mm-dd");
		Date date = null;
		try {
			date = formatter.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Criteria crit = session.createCriteria(Video.class);
		//crit.add(Restrictions.ge("CapturedDateTime", date));
		
		if(searchEntireDay)
			// setDate truncates the date object to just the date portion and then defaults the time to 00:00:00
			//query.setDate("dateTimeParam", date);
			crit.add(Restrictions.ge("CapturedDateTime", removeTime(date)));
		else
			//query.setTimestamp("dateTimeParam", date);
			crit.add(Restrictions.ge("CapturedDateTime", date));
		
		crit.addOrder(Order.desc("CapturedDateTime"));
		
		//query.setResultTransformer( Transformers.aliasToBean(Video.class));
		/*
		for(final Object o: query.list())
		{
			results.add((Video) o);
		}
		*/
		//results = query.list();
		// Used for type safety
		for(final Object o: crit.list())
		{
			results.add((Video) o);
		}
		tx.commit();
		session.close();
		return results;
	}

	@Override
	public List<Video> findVideoByCapturedDateTime(String datetime) {
		return findVideoByCapturedDateTime(datetime, true);
	}

	@Override
	public List<Video> findVideoByCapturedDateTimeRange(String datetime, long range) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(Video.class);
		/*
		Query query = session.createQuery("from Video where CapturedDateTime>:startdateTimeParam AND "
				+ "CapturedDateTime<:enddateTimeParam "
				+ "ORDER BY CapturedDateTime desc");
		final List<Video> results = new LinkedList<Video>();
		*/
		Date startdate = new Date(datetime);
		Date enddate = new Date(datetime + range);
		/*
		query.setTimestamp("startdateTimeParam", startdate);
		query.setTimestamp("enddateTimeParam", enddate);
		
		for(final Object o: query.list())
		{
			results.add((Video) o);
		}
		*/
		crit.add(Restrictions.between("CapturedDateTime", startdate, enddate));
		
		tx.commit();
		session.close();
		return crit.list();
		//return results;
	}
	
	 private static Date removeTime(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }

}
