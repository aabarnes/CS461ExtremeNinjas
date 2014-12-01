package edu.iastate.cs.proj461;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

public class VideoDAOImpl implements VideoDAO{
	
	private SessionFactory sf;

	public VideoDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Video> findVideoByCapturedDateTime(long datetime, boolean searchEntireDay) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Video where CapturedDateTime>:dateTimeParam");
		final List<Video> results = new LinkedList<Video>();
		Date date = new Date(datetime);
		if(searchEntireDay)
			// setDate truncates the date object to just the date portion and then defaults the time to 00:00:00
			query.setDate("dateTimeParam", date);
		else
			query.setTimestamp("dateTimeParam", date);
		
		//query.setResultTransformer( Transformers.aliasToBean(Video.class));
		for(final Object o: query.list())
		{
			results.add((Video) o);
		}
		//results = query.list();
		tx.commit();
		session.close();
		return results;
	}

	@Override
	public List<Video> findVideoByCapturedDateTime(long datetime) {
		return findVideoByCapturedDateTime(datetime, true);
	}

	@Override
	public List<Video> findVideoByCapturedDateTimeRange(long datetime, long range) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Video where CapturedDateTime>:startdateTimeParam AND "
				+ "CapturedDateTime<:enddateTimeParam");
		final List<Video> results = new LinkedList<Video>();
		Date startdate = new Date(datetime);
		Date enddate = new Date(datetime + range);
		query.setTimestamp("startdateTimeParam", startdate);
		query.setTimestamp("enddateTimeParam", enddate);
		
		for(final Object o: query.list())
		{
			results.add((Video) o);
		}
		tx.commit();
		session.close();
		return results;
	}

}
