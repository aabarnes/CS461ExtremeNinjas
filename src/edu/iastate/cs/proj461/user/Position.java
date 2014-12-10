package edu.iastate.cs.proj461.user;

/**
 * Position at the hospital, containing a job title and its 
 * corresponding role number.
 */
public class Position {
	
	private int roleID;
	private String title;
	
	public enum Role {
		SYSTEM_ADMIN(1),
		ADMIN(2),
		DOCTOR(3),
		NURSE(4);
		
		private Role(int value) {
			this.value = value;
		}
		
		public int getRoleValue() {
			return value;
		}
		private int value;
	}
	
	public Position() {
		
	}
	
	
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
