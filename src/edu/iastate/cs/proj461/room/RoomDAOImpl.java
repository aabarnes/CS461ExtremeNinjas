package edu.iastate.cs.proj461.room;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class RoomDAOImpl implements RoomDAO{
	
	private SessionFactory sf;

	public RoomDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Room findRoomById(int id) {
		Session session = null;
		Transaction tx = null;

		Room room;
		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			room = (Room) session.get(Room.class, id);
			tx.commit();
			return room;
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
	public Room findRoomByName(String name) {
		Session session = null;
		Transaction tx = null;

		Room room;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Room.class);
			crit.add(Restrictions.eq("name", name));
			room = (Room) crit.uniqueResult();
			tx.commit();
			return room;
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
	public List<String> listRoomNames() {
		Session session = null;
		Transaction tx = null;

		final List<String> results = new LinkedList<String>();
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			//for(final Object o: session.createCriteria(Room.class).list())
			for(final Object o: session.createQuery("Select r.name from Room r").list())
			{
				//results.add( ((Room) o).getName() );
				results.add((String) o);
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
	public List<Room> findRoomsByName(String name) {
		Session session = null;
		Transaction tx = null;
		
		final List<Room> matchingRooms = new LinkedList<Room>();

		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Room.class);
			crit.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
			for(final Object o: crit.list())
			{
				matchingRooms.add((Room) o);
			}
			tx.commit();
			return matchingRooms;
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
	public List<Room> findIdleRooms(int withinNumDays) {
		Session session = null;
		Transaction tx = null;
		
		final List<Room> matchingRooms = new LinkedList<Room>();

		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Room.class);
			Date currentDate = removeTime(new Date());
			Date thresholdDate = new Date(currentDate.getTime() - TimeUnit.DAYS.toMillis(withinNumDays));
			crit.add(Restrictions.lt("lastCapture", thresholdDate));
			for(final Object o: crit.list())
			{
				matchingRooms.add((Room) o);
			}
			tx.commit();
			return matchingRooms;
		}
		catch (Exception ex) {
			if(tx != null) tx.rollback();
			throw ex;
		}
		finally {
			session.close();
		}
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