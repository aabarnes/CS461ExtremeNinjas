package edu.iastate.cs.proj461.machine;

import java.util.List;

public interface MachineDAO {
	
	public List<Machine> getAllMachines();
	public Machine getMachine(int id);
	public Machine getMachine(String ipAddress);
	public List<String> getMachineIps();
	public void addMachine(Machine machine);
}