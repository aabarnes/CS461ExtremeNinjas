package edu.iastate.cs.proj461.video;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

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
	
	@Override
	public String execute() {
		
		List<Video> resultList;
		VideoDAO videoDAO = new VideoDAOImpl(HibernateUtil.getSessionFactory());
		
		//Fetch data from database here
		//	Cast to corresponding class
		resultList = (List<Video>) videoDAO.findVideoByCapturedDateTime((String) request.getAttribute("datetime"), 
				(boolean) request.getAttribute("searchEntireDay"));
		
		//Assign fetched data to return object here
		returnObj.setData(resultList);
				
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
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
	}

	public DatatableObject getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(DatatableObject returnObj) {
		this.returnObj = returnObj;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;		
	}
}
