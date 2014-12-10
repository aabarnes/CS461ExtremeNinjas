package edu.iastate.cs.proj461.video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.TemporalType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.TimestampType;

public class VideoDAOImpl implements VideoDAO{
	
	private SessionFactory sf;

	public VideoDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Video> findVideoByCapturedDateTime(String datetime) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		final List<Video> results = new LinkedList<Video>();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Date startOfDay = null;
		Date testDate = null;
		java.sql.Date sqlDate = null;
		try {
			startOfDay = formatter.parse(datetime);
			testDate = formatter.parse("2014-12-07 04:16:00");
			sqlDate = new java.sql.Date(startOfDay.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date endOfDay = new java.sql.Date(startOfDay.getTime() + TimeUnit.DAYS.toMillis(1) - 1);
		//java.sql.Timestamp sqlDateStart = new java.sql.Timestamp(startOfDay.getTime());
		//java.sql.Timestamp sqlDateEnd = new java.sql.Timestamp(endOfDay.getTime()); 
		
		//String q = "from Video v where v.CapturedDateTime between :start_date and :end_date";
		//String q = "from Video v where v.CapturedVideoName = :name";
		//String q = "from Video v where v.CapturedDateTime >= :start_date";
		String q = "from Video v";
		Query query = session.createQuery(q);
		//query.setParameter("start_date", sqlDate);
		//query.setParameter("start_date", sqlDateStart);
		//query.setParameter("end_date", endOfDay);
		//query.setParameter("test_date", testDate);
		//query.setParameter("id", 0);
		//System.out.println(sqlDate);
		//System.out.println(sqlDateEnd);
		//query.setParameter("start", startOfDay);
		//query.setParameter("name", "pKoNh4k6c0DGtjq");
		//query.setResultTransformer( Transformers.aliasToBean(Video.class));
		/*
		for(final Object o: query.list())
		{
			results.add((Video) o);
		}
		*/
		//results = query.list();
		// Used for type safety
		//for(final Object o: crit.list())
		for(final Object o: query.list())
		{
			results.add((Video) o);
		}
		tx.commit();
		session.close();
		return results;
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

	@Override
	public List<Video> findVideoByCapturedDateTimeAndRoom(String datetime,
			int roomID) {
		Session session = null;
		Transaction tx = null;

		final List<Video> results = new LinkedList<Video>();
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			//for(final Object o: session.createCriteria(Room.class).list())
			String hql = "from Video v where v.CapturedDateTime = :datetime";
			if(roomID >= 0)
				hql += "and v.roomID = :roomId";
			Query query = session.createQuery(hql);
			if(roomID >= 0)
				query.setParameter("roomId", roomID);
			query.setParameter("datetime", datetime);
			for(final Object o: query.list())
			{
				//results.add( ((Room) o).getName() );
				results.add((Video) o);
			}
			tx.commit();
			return results;
		}
		catch (Exception ex) {
			if(tx != null) tx.rollback();
			throw ex;
		}
		finally {
			session.close();
		}
	}
	
	@Override
	public List<Video> findAllVideos() {
		Session session = null;
		Transaction tx = null;

		final List<Video> results = new LinkedList<Video>();
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			for(final Object o: session.createCriteria(Video.class).list())
			{
				results.add((Video) o);
			}
			tx.commit();
			return results;
		}
		catch (Exception ex) {
			if(tx != null) tx.rollback();
			throw ex;
		}
		finally {
			session.close();
		}
	}

}
