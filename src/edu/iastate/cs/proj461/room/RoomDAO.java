package edu.iastate.cs.proj461.room;

import java.util.List;

public interface RoomDAO {
	
	public Room findRoomById(int id);
	public Room findRoomByName(String name);
	public List<Room> findRoomsByName(String name);
	public List<String> listRoomNames();
	public List<Room> findIdleRooms(int withinNumDays);
	public void addRoom(Room room);

}
