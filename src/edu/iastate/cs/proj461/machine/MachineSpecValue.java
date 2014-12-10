package edu.iastate.cs.proj461.machine;


public class MachineSpecValue {

	
	private int machineID;
	private int softwareCode;
	private String value;
	
	MachineSpecValue() {
		
	}
	
	public MachineSpecValue(Machine machine, MachineSoftware software){
		this.machineID = machine.getId();
		this.softwareCode = software.getCode();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}

	public int getSoftwareCode() {
		return softwareCode;
	}

	public void setSoftwareCode(int softwareCode) {
		this.softwareCode = softwareCode;
	}


}
