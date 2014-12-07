package edu.iastate.cs.proj461.room;

import java.util.List;

public interface RoomDAO {
	
	Room findRoomById(int id);
	Room findRoomByName(String name);
	List<Room> findRoomsByName(String name);
	List<String> listRoomNames();

}
