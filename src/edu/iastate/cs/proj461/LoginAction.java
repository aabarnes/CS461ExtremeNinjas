package edu.iastate.cs.proj461;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import edu.iastate.cs.proj461.user.Position;
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
    
    private User user;
 
    @Override
    public String execute() {
		return Action.SUCCESS;
    }
	
	@Override
	public void validate() {
		
		UserDAO userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		user = userDAO.getUserByUsername(username);
		if(user != null && passwordEncryptor.checkPassword(password, user.getPassword())) {
			sessionMap.put("user", user);
			sessionMap.put("role", Position.Role.values()[user.getPos().getRoleID() - 1].name());
		}
		else {
			addActionError("Incorrect Username/Password.");
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
    
}