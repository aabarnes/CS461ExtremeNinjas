package edu.iastate.cs.proj461.user;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import edu.iastate.cs.proj461.DatatableObject;
import edu.iastate.cs.proj461.util.HibernateUtil;

public class ListUsersAction extends ActionSupport {
	
	private DatatableObject returnObj;
	
	public String getAllUsers(){
		List<User> users = new ArrayList<User>();
		
		UserDAO userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		users = userDAO.getAllUsers();
		
		returnObj = new DatatableObject();
		returnObj.setData(users);
		
		return Action.SUCCESS;
	}

}
