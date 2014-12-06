package edu.iastate.cs.proj461.machine;

public class MachineSpecValue {
	
	private int code;
	private int machineID;
	private String value;
	
	public MachineSpecValue(int code, int machineID, String value){
		this.code = code;
		this.machineID = machineID;
		this.value = value;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getMachineID() {
		return machineID;
	}
	
	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
