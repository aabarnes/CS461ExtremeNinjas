package edu.iastate.cs.proj461.room;

import java.util.Date;

public class Room {
	
	private int roomID;
	private String name;
	private Date lastCapture;
	private String machineIP;
	
	public Room(int roomID, String name, Date lastCapture, String machineIP) {
		this.roomID = roomID;
		this.name = name;
		this.lastCapture = lastCapture;
		this.machineIP = machineIP;
	}
	
	public Room(int roomID, String name, String machineIP) {
		this(roomID, name, null, machineIP);
	}
	
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastCapture() {
		return lastCapture;
	}
	public void setLastCapture(Date lastCapture) {
		this.lastCapture = lastCapture;
	}
	public String getMachineIP() {
		return machineIP;
	}
	public void setMachineIP(String machineIP) {
		this.machineIP = machineIP;
	}

}
