package edu.iastate.cs.proj461.user;

public interface UserDAO {
	
	public User getUserByCredentials(String username, String password);
	public Position getUserPosition(String username);

}