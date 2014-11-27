package edu.iastate.cs.proj461;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;

public class VideoCaptureSearchResultsAction implements ServletRequestAware {

	
	private HttpServletRequest request;
	
	private DatatableObject returnObj;

	public String execute() {
		
		//Dummy data
		int id = 1;
		List<DummyClass> resultList = new ArrayList<DummyClass>();
		resultList.add(new DummyClass(id++, "Patrick", "p@ssw0rd"));
		resultList.add(new DummyClass(id++, "Chloe", "p@w"));
		resultList.add(new DummyClass(id++, "Laruen", "sw0rd"));
		resultList.add(new DummyClass(id++, "Carly", "roomie"));
		resultList.add(new DummyClass(id++, "Megan", "p@ssrd"));
		resultList.add(new DummyClass(id++, "Ben", "p@ssw0rdStrong"));
		
		returnObj = new DummyClassDatatableObject();
		returnObj.setiTotalDisplayRecords(resultList.size());
		returnObj.setiTotalRecords(resultList.size());
		returnObj.setsEcho(request.getParameter("sEcho"));
		
		//Fetch data from database here
		
		//Assign fetched data to return object here
		//	Cast to corresponding class
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
