package edu.iastate.cs.proj461.user;

import java.util.List;

public interface UserDAO {
	
	public User getUserByCredentials(String username, String password);
	public Position getUserPosition(String username);
	public List<User> getAllUsers();

}
