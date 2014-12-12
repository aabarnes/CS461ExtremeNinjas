package edu.iastate.cs.proj461.machine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MachineSpecValueDAOImpl implements MachineSpecValueDAO{
	
	SessionFactory sf;
	
	public MachineSpecValueDAOImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	public MachineSpecValue getValue(MachineSpecValuePK MachineSpecValuePK) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		MachineSpecValue machineSpecValue = (MachineSpecValue) session.get(MachineSpecValue.class, MachineSpecValuePK); 
		tx.commit();
		session.close();
		return machineSpecValue;
	}

	@Override
	public void addMachineSpecValue(MachineSpecValue machineSpecValue) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(machineSpecValue);
		tx.commit();
		session.close();
	}

	@Override
	public void updateMachineSpecValue(MachineSpecValue machineSpecValue) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(machineSpecValue);
		tx.commit();
		session.close();		
	}

}
