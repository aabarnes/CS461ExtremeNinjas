package edu.iastate.cs.proj461;

import com.opensymphony.xwork2.ActionSupport;
 
public class LogoutAction extends ActionSupport{
    private String username;
    private String password;
 
    public String execute() {
    	return "success";
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