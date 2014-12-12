package edu.iastate.cs.proj461.machine;

import java.util.List;

public interface MachineDAO {
	
	public List<Machine> getAllMachinesInfo();
	public Machine getMachineInfo(int id);
	public Machine getMachineInfo(String ipAddress);
	public List<Machine> getAllMachines();
	public List<String> getMachineIps();
	public void addMachine(Machine machine);

}
