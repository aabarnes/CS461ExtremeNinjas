package edu.iastate.cs.proj461.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;

import edu.iastate.cs.proj461.machine.Machine;
import edu.iastate.cs.proj461.machine.MachineDAO;
import edu.iastate.cs.proj461.machine.MachineDAOImpl;
import edu.iastate.cs.proj461.machine.MachineSoftware;
import edu.iastate.cs.proj461.machine.MachineSoftwareDAO;
import edu.iastate.cs.proj461.machine.MachineSoftwareDAOImpl;
import edu.iastate.cs.proj461.machine.MachineSpecValue;
import edu.iastate.cs.proj461.machine.MachineSpecValueDAO;
import edu.iastate.cs.proj461.machine.MachineSpecValueDAOImpl;
import edu.iastate.cs.proj461.machine.MachineSpecValuePK;
import edu.iastate.cs.proj461.room.Room;
import edu.iastate.cs.proj461.room.RoomDAO;
import edu.iastate.cs.proj461.room.RoomDAOImpl;
import edu.iastate.cs.proj461.user.Position;
import edu.iastate.cs.proj461.user.PositionDAO;
import edu.iastate.cs.proj461.user.PositionDAOImpl;
import edu.iastate.cs.proj461.user.User;
import edu.iastate.cs.proj461.user.UserDAO;
import edu.iastate.cs.proj461.user.UserDAOImpl;
import edu.iastate.cs.proj461.util.HibernateUtil;
import edu.iastate.cs.proj461.video.Video;
import edu.iastate.cs.proj461.video.VideoDAO;
import edu.iastate.cs.proj461.video.VideoDAOImpl;

public class GenerateDummyData {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/mydb";

	// Database credentials
	static final String USER = "test";
	static final String PASS = "test";

	static Connection conn = null;
	
	private final static int numMachines = 20;
	private final static int numUsers = 10;
	static List<String> ipAddresses;
	static Map<Integer, Integer> roomMachine;
	

	private enum State {
		UP,
		DOWN
	}

	public static void generateData() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//conn.setAutoCommit(false);

			ipAddresses = new ArrayList<String>(numMachines);
			roomMachine = new LinkedHashMap<Integer, Integer>();
						
			// machine
			GenerateDummyMachines();
			
			// machine software
			GenerateDummySoftware();
			
			// machine spec values
			GenerateMachineSpecs();
			
			// positions
			GeneratePositions();
			
			// user
			GenerateUsers();
			
			// room
			GenerateRooms();
			
			// video
			GenerateVideos();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}


	private static void GenerateDummyMachines() {
		Random random = new Random();
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());

		for(int i = 0; i < numMachines; i++) {
			
			Machine machine = new Machine();
			machine.setMachineIP(generateRandomIP());
			machine.setCaptureState(State.values()[random.nextInt(State.values().length)].name());
			machine.setDiskState(State.values()[random.nextInt(State.values().length)].name());
			machine.setMachineState(State.values()[random.nextInt(State.values().length)].name());
			
			machineDAO.addMachine(machine);
		}
	}
	
	private static void GenerateDummySoftware() {
		String list = "Remaining Disk Space (in GBs)";
		MachineSoftwareDAO machineSoftwareDAO = new MachineSoftwareDAOImpl(HibernateUtil.getSessionFactory());
		for(MachineSoftware machineSoftware : machineSoftwareDAO.getAllSoftwareCodes())
		{
			if(machineSoftware.getDescription().equals(list))
				return;
		}
		for(int i = 0; i < 1; i++) {
			MachineSoftware machineSoftware = new MachineSoftware();
			machineSoftware.setDescription(list);
			machineSoftwareDAO.addMachineSoftware(machineSoftware);
		}
	}
	
	private static void GenerateMachineSpecs() {
		Random random = new Random();
		List<Integer> machines = new ArrayList<Integer>(numMachines);
		MachineSpecValueDAO machineSpecValueDAO = new MachineSpecValueDAOImpl(HibernateUtil.getSessionFactory());
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());
		MachineSoftwareDAO machineSoftwareDAO = new MachineSoftwareDAOImpl(HibernateUtil.getSessionFactory());
		for(int i = 0; i < numMachines; i++) {
			MachineSpecValue machineSpecValue = new MachineSpecValue();
			int temp;
			do {
				temp = random.nextInt(numMachines);
			} while(machines.contains(temp));
			machines.add(temp);
			MachineSoftware machineSoftware = machineSoftwareDAO.getMachineSoftwareById(1);
			Machine machine = machineDAO.getMachineInfo(temp+1);
			machineSpecValue.setMachineSpecValueId(new MachineSpecValuePK(machine, machineSoftware));
			machineSpecValue.setValue("250");
			machineSpecValueDAO.updateMachineSpecValue(machineSpecValue);
		}
	}
	
	private static void GeneratePositions() {
		PositionDAO positionDAO = new PositionDAOImpl(HibernateUtil.getSessionFactory());
		for(int i = 0; i < 1; i++) {
			Position position = new Position();
			position.setTitle("CEO");
			positionDAO.addPostion(position);
		}
	}
	
	private static void GenerateUsers() {
		Random random = new Random();
		UserDAO userDAO = new UserDAOImpl(HibernateUtil.getSessionFactory());
		PositionDAO positionDAO = new PositionDAOImpl(HibernateUtil.getSessionFactory());
		for(int i = 0; i < numUsers; i++) {
			User user = new User();
			user.setFirstName(RandomStringUtils.random(random.nextInt(10) + 1, true, false));
			user.setLastName(RandomStringUtils.random(random.nextInt(10) + 1, true, false));
			user.setUserName(RandomStringUtils.random(random.nextInt(10) + 1, true, true));
			user.setPassword(RandomStringUtils.random(random.nextInt(10) + 1, true, true));
			user.setEmail(RandomStringUtils.random(random.nextInt(10) + 1, true, true));
			Position position = positionDAO.getPositionById(1);
			user.setPos(position);
			userDAO.addUser(user);
		}
	}
	
	private static void GenerateRooms() {
		List<Integer> machines = new ArrayList<Integer>(numMachines);
		Random random = new Random();
		RoomDAO roomDAO = new RoomDAOImpl(HibernateUtil.getSessionFactory());
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());

		for(int i = 0; i < numMachines; i++) {
			Room room = new Room();
			room.setName(RandomStringUtils.random((int)(Math.random() * 5) + 5, true, false));
			room.setLastCapture(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(30)));
			int temp;
			do {
				temp = random.nextInt(numMachines) + 1;
			} while(machines.contains(temp));
			machines.add(temp);
			Machine machine = machineDAO.getMachineInfo(temp);
			if(machine == null)
				System.out.println("Machine null at id: " + temp);
			room.setMachine(machine);
			roomMachine.put(i+1, temp);
			roomDAO.addRoom(room);
		}
	}
	
	private static void GenerateVideos() {
		Random random = new Random();
		VideoDAO videoDAO = new VideoDAOImpl(HibernateUtil.getSessionFactory());
		RoomDAO roomDAO = new RoomDAOImpl(HibernateUtil.getSessionFactory());
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());

		for(int i = 0; i < numUsers * 10; i++) {
			Video video = new Video();
			video.setCapturedVideoName(RandomStringUtils.random(15, true, true));
			int numDaysBackCapture = random.nextInt(15);
			video.setCapturedDateTime(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(numDaysBackCapture)));
			int analysisDay;
			do {
				analysisDay = random.nextInt(15);
			} while(analysisDay > numDaysBackCapture);
			video.setDateAnalysisDone(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(analysisDay)));
			video.setUploadedFileName(RandomStringUtils.random(15, true, true));
			video.setAnalysisDirName(RandomStringUtils.random(15, true, true));
			Room room = roomDAO.findRoomById(random.nextInt(numMachines)+1);
			video.setRoom(room);
			video.setSize((int)(Math.random() * 1024));
			video.setLength((int)(Math.random() * 1024));
			Machine machine = machineDAO.getMachineInfo(roomMachine.get(room.getRoomID()));
			if(machine == null)
				System.out.println("Machine null with id: " + roomMachine.get(room.getRoomID()));
			video.setMachine(machine);
			videoDAO.addVideo(video);
		}
	}

	private static String generateRandomIP() {
		Random random = new Random((new Date()).getTime());
		String s;
		do {
		s = "";
		s += Integer.toString(random.nextInt(255));
		s += ".";
		s += Integer.toString(random.nextInt(255));
		s += ".";
		s += Integer.toString(random.nextInt(255));
		s += ".";
		s += Integer.toString(random.nextInt(255));
		}
		while(ipAddresses.contains(s));
		/*
		if(ipAddresses.contains(s))
			s =  generateRandomIP();
		else
			ipAddresses.add(s);
			*/
		ipAddresses.add(s);
		return s;
	}

}
