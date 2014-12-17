package edu.iastate.cs.proj461.disk;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.iastate.cs.proj461.machine.Machine;
import edu.iastate.cs.proj461.user.User;

public class DiskDAOImpl implements DiskDAO {

	private SessionFactory sf;
	
	public DiskDAOImpl(SessionFactory sf){
		this.sf = sf;
	}
	
	@Override
	public void addDisk(Disk disk) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(disk);
		tx.commit();
		session.close();
		
	}

	@Override
	public void updateDisk(Disk disk) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(disk);
		tx.commit();
		session.close();
	}

	@Override
	public List<Disk> getMachineDisks(int machineId) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Disk d where d.diskPK.machine.id = :mid");
		query.setParameter("mid", machineId);
		//new Disk().getDiskPK().getDiskId()
		List<Disk> disks = query.list();
				 
		tx.commit();
		session.close();
		return disks;
	}

	@Override
	public void removeDisk(Disk disk) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(disk);
		tx.commit();
		session.close();
		
	}

}
