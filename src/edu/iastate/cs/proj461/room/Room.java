package edu.iastate.cs.proj461.room;

import java.util.Date;

import edu.iastate.cs.proj461.machine.Machine;

public class Room {
	
	private int roomID;
	private String name;
	private Date lastCapture;
	private Machine machine;
	
	public Room() {
		
	}
	
	public Room(int roomID, String name, Date lastCapture, Machine machine) {
		this.roomID = roomID;
		this.name = name;
		this.lastCapture = lastCapture;
		this.machine = machine;
	}
	
	public Room(int roomID, String name, Machine machine) {
		this(roomID, name, null, machine);
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
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}
