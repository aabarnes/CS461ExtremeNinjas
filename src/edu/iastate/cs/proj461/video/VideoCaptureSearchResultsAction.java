package edu.iastate.cs.proj461.video;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;

import edu.iastate.cs.proj461.util.HibernateUtil;

public class VideoCaptureSearchResultsAction implements ServletRequestAware {

	
	private HttpServletRequest request;
	
	private DatatableObject returnObj;

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

	public DatatableObject getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(DatatableObject returnObj) {
		this.returnObj = returnObj;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletRequest getServletRequest() {
		return request;
	}
}
