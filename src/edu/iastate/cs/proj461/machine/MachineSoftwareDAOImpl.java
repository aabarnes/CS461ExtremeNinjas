package edu.iastate.cs.proj461.machine;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.iastate.cs.proj461.room.Room;

public class MachineSoftwareDAOImpl implements MachineSoftwareDAO{
	
	SessionFactory sf;
	
	public MachineSoftwareDAOImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	public MachineSoftware getMachineSoftwareById(int id) {
		Session session = null;
		Transaction tx = null;

		MachineSoftware machineSoftware;
		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			machineSoftware = (MachineSoftware) session.get(MachineSoftware.class, id);
			tx.commit();
			return machineSoftware;
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
	public void addMachineSoftware(MachineSoftware machineSoftare) {
		Session session = null;
		Transaction tx = null;		
		
		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			session.persist(machineSoftare);
			tx.commit();
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
	public List<MachineSoftware> getAllSoftwareCodes() {
		Session session = null;
		Transaction tx = null;

		List<MachineSoftware> machineSoftwareList = new ArrayList<MachineSoftware>();
		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(MachineSoftware.class);
			for(final Object o: crit.list())
			{
				machineSoftwareList.add((MachineSoftware) o);
			}
			tx.commit();
			return machineSoftwareList;
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
