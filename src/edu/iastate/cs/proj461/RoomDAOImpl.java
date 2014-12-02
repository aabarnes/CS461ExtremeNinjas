package edu.iastate.cs.proj461;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	public List<Room> findRoomByName(String name) {
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
	public List<String> listRoomNames() {
		Session session = null;
		Transaction tx = null;

		final List<String> results = new LinkedList<String>();
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			for(final Object o: session.createCriteria(Room.class).list())
			{
				results.add( ((Room) o).getName() );
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