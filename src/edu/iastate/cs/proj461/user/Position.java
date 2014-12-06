package edu.iastate.cs.proj461.user;

/**
 * Position at the hospital, containing a job title and its 
 * corresponding role number.
 */
public class Position {
	
	private int roleID;
	private String title;
	
	
	public Position(int roleID, String title){
		this.roleID = roleID;
		this.title = title;
	}
	
	public int getRoleID() {
		return roleID;
	}
	
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
