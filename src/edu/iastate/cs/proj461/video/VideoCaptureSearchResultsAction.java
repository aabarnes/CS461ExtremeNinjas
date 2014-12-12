package edu.iastate.cs.proj461.video;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Hibernate;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import edu.iastate.cs.proj461.DatatableObject;
import edu.iastate.cs.proj461.user.Position;
import edu.iastate.cs.proj461.user.UserDAO;
import edu.iastate.cs.proj461.user.UserDAOImpl;
import edu.iastate.cs.proj461.util.HibernateUtil;

public class VideoCaptureSearchResultsAction extends ActionSupport implements SessionAware {

	
	private HttpServletRequest request;
	
	private DatatableObject returnObj;
	
	private SessionMap<String, Object> sessionMap;
	
	private String datetime = null;
	private String roomIDString = null;
	
	@Override
	public String execute() {
		
		List<Video> resultList;
		SimpleDateFormat dateFormat;
		VideoDAO videoDAO = new VideoDAOImpl(HibernateUtil.getSessionFactory());
		
		//Fetch data from database here
		//	Cast to corresponding class
		
		//String fromJSP = (String) request.getAttribute("datetime");
		//System.out.println(fromJSP);
		//String datetime = (String) request.getAttribute("datetime");
		if(datetime == null) {
			System.out.println(datetime);
			//dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			//datetime = (new Date().toString());
			datetime = "2014-12-07";
		}
		//int roomID = (int) request.getAttribute("room");
		int roomID = Integer.parseInt(roomIDString);
		System.out.println(roomID);
		
		resultList = (List<Video>) videoDAO.findVideoByCapturedDateTimeAndRoom(datetime, roomID);
		//resultList = (List<Video>) videoDAO.findAllVideos();
		
		//Assign fetched data to return object here
		returnObj = new DatatableObject();
		returnObj.setData(resultList);
		Hibernate.initialize(returnObj);
				
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		/*
		UserDAO userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		String username = (String) sessionMap.get("username");
		int positionID = -1;
		if(username != null)
			positionID = userDAO.getUserPosition(username).getRoleID();
		else {
			addActionError("Not currently logged in.");
		}

		if(!(positionID == Position.Role.SYSTEM_ADMIN.getRoleValue() ||
				positionID == Position.Role.ADMIN.getRoleValue() ||
				positionID == Position.Role.DOCTOR.getRoleValue() ||
				positionID == Position.Role.NURSE.getRoleValue())) {
			addActionError("Insufficient Privileges.");
		}
		*/
	}

	public DatatableObject getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(DatatableObject returnObj) {
		this.returnObj = returnObj;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getRoomIDString() {
		return roomIDString;
	}

	public void setRoomIDString(String roomIDString) {
		this.roomIDString = roomIDString;
	}
}
