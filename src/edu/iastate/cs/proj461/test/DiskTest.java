package edu.iastate.cs.proj461.test;

import java.util.List;

import edu.iastate.cs.proj461.disk.Disk;
import edu.iastate.cs.proj461.disk.DiskDAO;
import edu.iastate.cs.proj461.disk.DiskDAOImpl;
import edu.iastate.cs.proj461.util.HibernateUtil;

public class DiskTest {
	
	public static void main(String[] args) {
		HibernateUtil.initSessionFactory();
		DiskDAO diskDAO = new DiskDAOImpl(HibernateUtil.getSessionFactory());
		
		List<Disk> disks = diskDAO.getMachineDisks(1);
		
		for(Disk d: disks){
			printDisk(d);
		}
	}
	
	public static void printDisk(Disk d){
		System.out.println("MachineId: " + d.getDiskPK().getMachine().getId() 
							+ "\tDiskId: " + d.getDiskId()
							+ "\tCapacity: " + d.getCapacity()
							+ "\tPercentFull: " + d.getPercentFull()
						);
	}

}
