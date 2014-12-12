package edu.iastate.cs.proj461.test;

import java.util.List;

import com.opensymphony.xwork2.Action;

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

public class TestAction {
	
	VideoDAO videoDAO;
	UserDAO userDAO;
	MachineDAO machineDAO;
	
	public List<User> userList;
	public List<Video> videoList;
	public List<Machine> machineList;
	
	public String execute() {
		videoDAO = new VideoDAOImpl(HibernateUtil.getSessionFactory());
		userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());
		
		userList = userDAO.getAllUsers();
		videoList = videoDAO.findAllVideos();
		machineList = machineDAO.getAllMachines();
		
		return Action.SUCCESS;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

}
