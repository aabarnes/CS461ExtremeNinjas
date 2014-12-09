package edu.iastate.cs.proj461.machine;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MachineDAOImpl implements MachineDAO{
	
	private SessionFactory sf;
	
	public MachineDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Machine> getAllMachinesInfo() {
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

}
