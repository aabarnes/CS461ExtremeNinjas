package edu.iastate.cs.proj461.disk;

import java.util.List;

import edu.iastate.cs.proj461.machine.Machine;

public interface DiskDAO {
	public void addDisk(Disk disk);
	public void updateDisk(Disk disk);
	public List<Disk> getMachineDisks(int machineId);
	public void removeDisk(Disk disk);
}
