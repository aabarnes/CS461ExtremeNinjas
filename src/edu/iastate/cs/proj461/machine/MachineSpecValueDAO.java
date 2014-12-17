package edu.iastate.cs.proj461.machine;

import java.util.List;

public interface MachineSpecValueDAO {
	
	public MachineSpecValue getValue(MachineSpecValuePK machineSPecValuePK);
	public void addMachineSpecValue(MachineSpecValue machineSpecValue);
	public void updateMachineSpecValue(MachineSpecValue machineSpecValue);
	public List<MachineSpecValue> getSoftwareForMachine(String ipAddress);
}
