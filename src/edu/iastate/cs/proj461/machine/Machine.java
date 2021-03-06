package edu.iastate.cs.proj461.machine;

public class Machine {
	
	private int id;
	private String machineIP;
	private String captureState;
	private String machineState;
	
	public Machine() {
		
	}
	
	public Machine(int id, String machineIP, String captureState, String machineState){
		this.id = id;
		this.machineIP = machineIP;
		this.captureState = captureState;
		this.machineState = machineState;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMachineIP() {
		return machineIP;
	}
	
	public void setMachineIP(String machineIP) {
		this.machineIP = machineIP;
	}
	
	public String getCaptureState() {
		return captureState;
	}
	
	public void setCaptureState(String captureState) {
		this.captureState = captureState;
	}
	
	public String getMachineState() {
		return machineState;
	}
	
	public void setMachineState(String machineState) {
		this.machineState = machineState;
	}
	
}
