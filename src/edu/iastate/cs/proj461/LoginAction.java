package edu.iastate.cs.proj461;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import edu.iastate.cs.proj461.user.User;
import edu.iastate.cs.proj461.user.UserDAO;
import edu.iastate.cs.proj461.user.UserDAOImpl;
import edu.iastate.cs.proj461.util.HibernateUtil;
import edu.iastate.cs.proj461.video.VideoDAO;
import edu.iastate.cs.proj461.video.VideoDAOImpl;
 
public class LoginAction extends ActionSupport implements SessionAware{
    private String username;
    private String password;
    
    private SessionMap<String, Object> sessionMap;
    
    //private User user = new User();
 
    public String execute() {
 
		UserDAO userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		User userDB = userDAO.getUserByCredentials(username, password);
		if(userDB == null)
			return Action.ERROR;
		else {
			sessionMap.put("username", username);
			return Action.SUCCESS;
		}
    }

	@Override
	public void setSession(Map<String, Object> map) {
    	sessionMap = (SessionMap<String, Object>) map;
	}
	
	public String logout() {
		sessionMap.invalidate();
		return Action.SUCCESS;
	}
 
    /*
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    */
}