package edu.iastate.cs.proj461.user;

import java.util.List;

public interface UserDAO {
	
	public User getUserByUsername(String username);
	public Position getUserPosition(String username);
	public List<User> getAllUsers();
	public void addUser(User user);
	public void updateUserInfo(User user);

}
