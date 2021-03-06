package edu.iastate.cs.proj461.machine;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class MachineDAOImpl implements MachineDAO{
	
	private SessionFactory sf;
	
	public MachineDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Machine getMachine(int id) {
		Session session = null;
		Transaction tx = null;

		final Machine machine;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Machine.class);
			crit.add(Restrictions.idEq(id));
			tx.commit();
			machine = (Machine) crit.uniqueResult();
			return machine;
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
	public Machine getMachine(String ipAddress) {
		Session session = null;
		Transaction tx = null;

		final Machine machine;
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Machine.class);
			crit.add(Restrictions.eq("machineIP", ipAddress));
			tx.commit();
			machine = (Machine) crit.uniqueResult();
			return machine;
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
	public List<Machine> getAllMachines() {
		Session session = null;
		Transaction tx = null;

		final List<Machine> results = new LinkedList<Machine>();
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			for(final Object o: session.createCriteria(Machine.class).list())
			{
				results.add( (Machine) o );
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
	
	public List<String> getMachineIps() {
		Session session = null;
		Transaction tx = null;

		final List<String> results = new LinkedList<String>();
		
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			for(final Object o: session.createCriteria(Machine.class).list())
			{
				results.add( ((Machine) o).getMachineIP() );
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
	public void addMachine(Machine machine) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(machine);
		tx.commit();
		session.close();
	}

}
