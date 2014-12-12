package edu.iastate.cs.proj461.machine;

public interface MachineSpecValueDAO {
	
	public MachineSpecValue getValue(MachineSpecValuePK machineSPecValuePK);
	public void addMachineSpecValue(MachineSpecValue machineSpecValue);
	public void updateMachineSpecValue(MachineSpecValue machineSpecValue);
}
