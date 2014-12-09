package edu.iastate.cs.proj461.user;

public class User {
	
	private int id;
	private String fName;
	private String lName;
	private String username;
	private String password;
	private String email;
	private Position pos;
	
	public User() {
		
	}
	
	public User(int id, String fName, String lName, String username, String password, String email, Position pos){
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.pos = pos;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getfName() {
		return fName;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
}
