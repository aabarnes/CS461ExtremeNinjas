package edu.iastate.cs.proj461.machine;


public class MachineSpecValue {
	
	private MachineSpecValuePK machineSpecValuePK;
	private String value;
	
	public MachineSpecValue() {
		
	}
	
	public MachineSpecValue(MachineSpecValuePK machineSpecValueId) {
		this.setMachineSpecValueId(machineSpecValueId);
	}

	public MachineSpecValuePK getMachineSpecValueId() {
		return machineSpecValuePK;
	}

	public void setMachineSpecValueId(MachineSpecValuePK machineSpecValuePK) {
		this.machineSpecValuePK = machineSpecValuePK;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
