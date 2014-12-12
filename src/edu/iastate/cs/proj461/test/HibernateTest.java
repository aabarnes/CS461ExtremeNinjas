package edu.iastate.cs.proj461.test;

import java.util.Date;
import java.util.List;

import edu.iastate.cs.proj461.machine.Machine;
import edu.iastate.cs.proj461.machine.MachineDAO;
import edu.iastate.cs.proj461.machine.MachineDAOImpl;
import edu.iastate.cs.proj461.user.User;
import edu.iastate.cs.proj461.user.UserDAO;
import edu.iastate.cs.proj461.user.UserDAOImpl;
import edu.iastate.cs.proj461.util.HibernateUtil;
import edu.iastate.cs.proj461.video.Video;
import edu.iastate.cs.proj461.video.VideoDAO;
import edu.iastate.cs.proj461.video.VideoDAOImpl;

public class HibernateTest {
		
	public static void main(String[] args) {
		VideoDAO videoDAO = new VideoDAOImpl(HibernateUtil.getSessionFactory());
		UserDAO userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());

		getVideos(videoDAO);
		getUsers(userDAO);
		getMachines(machineDAO);
		/*
		Date date = new Date();
		
		List<Video> resultList = (List<Video>) videoDAO.findVideoByCapturedDateTime("2014-12-07 00:00:00");
		System.out.println(resultList.size());
		for(Video video : resultList) {
			System.out.println(video.getCapturedDateTime());
		}
		
		User user = userDAO.getUserByCredentials("SABQKdc", "GKF");
		if(user == null)
			System.out.println("User is null");
		else
			System.out.println(user);
		*/
	}
	
	public static void getUsers(UserDAO userDAO) {
		List<User> results = userDAO.getAllUsers();
		for(User user: results) {
			System.out.println("FirstName: " + user.getFirstName());
			System.out.println("LastName: " + user.getLastName());
			System.out.println("Username: " + user.getUserName());
			System.out.println("Email: " + user.getEmail() + "\n");
		}
	}
	
	public static void getVideos(VideoDAO videoDAO) {
		List<Video> results = videoDAO.findAllVideos();
		for(Video video: results) {
			System.out.println(video.getCapturedDateTime());
		}
	}
	
	public static void getMachines(MachineDAO machineDAO) {
		List<Machine> results = machineDAO.getAllMachines();
		for(Machine machine: results) {
			System.out.println("Id: " + machine.getId());
			System.out.println("IP Address: " + machine.getMachineIP());
			System.out.println("State: " + machine.getMachineState() + "\n");
		}
	}

	//Fetch data from database here
	//	Cast to corresponding class


}
