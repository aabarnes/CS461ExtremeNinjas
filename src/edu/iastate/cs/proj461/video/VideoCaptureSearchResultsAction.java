package edu.iastate.cs.proj461.video;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import edu.iastate.cs.proj461.user.User;
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
		
		List<Video> resultList = null;
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
		int roomID = -1;
		if("All".equals(roomIDString));
		else if("".equals(roomIDString));
		else if(roomIDString == null);
		else
			roomID = Integer.parseInt(roomIDString);
		
		resultList = (List<Video>) videoDAO.findVideoByCapturedDateTimeAndRoom(datetime, roomID);
		//Hibernate.initialize(resultList);

		//resultList = (List<Video>) videoDAO.findAllVideos();
		/*
		System.out.println(resultList.size());
		int count = 0;
		for(Video video : resultList) {
			System.out.println(count++ + ": " + video.getCapturedVideoName() + "\t\t" + video.getRoom().getRoomID() + "\t\t" + video.getMachine().getId());
		}
		*/
		
		//Assign fetched data to return object here
		returnObj = new DatatableObject();
		if(resultList.size() > 0) {
			Hibernate.initialize(resultList);
			returnObj.setData(resultList);
		}
		else
			returnObj.setData(new ArrayList<Video>());
		Hibernate.initialize(returnObj);
		
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		
		User user = (User) sessionMap.get("user");
		Position.Role position;
		if(user == null)
			addActionError("Not currently logged in.");
		
		position = Position.Role.values()[user.getPos().getRoleID() - 1];

		if(!(position == Position.Role.SYSTEM_ADMIN ||
				position == Position.Role.ADMIN ||
				position == Position.Role.DOCTOR) ||
				position == Position.Role.NURSE) {
			addActionError("Insufficient Privileges.");
		}
		
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
