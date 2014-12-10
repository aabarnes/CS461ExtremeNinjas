package edu.iastate.cs.proj461.room;

import java.util.List;

import com.opensymphony.xwork2.Action;

import edu.iastate.cs.proj461.DatatableObject;
import edu.iastate.cs.proj461.util.HibernateUtil;

public class RoomSearchAction {
	
	private DatatableObject returnObj;

	public String getRoomNames() {
		
		List<String> roomNames;
		RoomDAO roomDAO = new RoomDAOImpl(HibernateUtil.getSessionFactory());
		
		roomNames = roomDAO.listRoomNames();
		System.out.println(roomNames);
		
		returnObj = new DatatableObject();
		returnObj.setData(roomNames);
		
		return Action.SUCCESS;
	}

	public DatatableObject getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(DatatableObject returnObj) {
		this.returnObj = returnObj;
	}

}
