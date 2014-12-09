package edu.iastate.cs.proj461.machine;

import java.util.List;

public interface MachineDAO {
	
	public List<Machine> getAllMachinesInfo();
	public Machine getMachineInfo(int id);
	public Machine getMachineInfo(String ipAddress);

}
