package edu.iastate.cs.proj461.machine;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.iastate.cs.proj461.disk.Disk;

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

	@Override
	public List<MachineSpecValue> getSoftwareForMachine(String ipAddress) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from MachineSpecValue specValue where specValue.machineSpecValuePK.machine.machineIP = :ipAddress");
		query.setParameter("ipAddress", ipAddress);
		//new Disk().getDiskPK().getDiskId()
		List<MachineSpecValue> results = query.list();
				 
		tx.commit();
		session.close();
		return results;
	}

}
