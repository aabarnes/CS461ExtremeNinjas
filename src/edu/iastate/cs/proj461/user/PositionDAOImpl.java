package edu.iastate.cs.proj461.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import edu.iastate.cs.proj461.machine.MachineSpecValue;
import edu.iastate.cs.proj461.room.Room;

public class PositionDAOImpl implements PositionDAO{
	
	SessionFactory sf;
	
	public PositionDAOImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	public void addPostion(Position position) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(position);
		tx.commit();
		session.close();
	}

	@Override
	public Position getPositionById(int id) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Position position = (Position) session.get(Position.class, id); 
			tx.commit();
			return position;
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
