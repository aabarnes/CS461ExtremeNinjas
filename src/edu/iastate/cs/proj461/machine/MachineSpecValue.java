package edu.iastate.cs.proj461.machine;

public class MachineSpecValue {
	
	private MachineSoftware software;
	private Machine machine;
	private String value;
	
	public MachineSpecValue(MachineSoftware software, Machine machine, String value){
		this.software = software;
		this.machine = machine;
		this.value = value;
	}

	public MachineSoftware getSoftware() {
		return software;
	}

	public void setSoftware(MachineSoftware software) {
		this.software = software;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
