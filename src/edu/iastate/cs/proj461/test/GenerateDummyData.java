package edu.iastate.cs.proj461.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

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

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);

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
			
		} catch (SQLException se) {
			se.printStackTrace();
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
		try {
				PreparedStatement psmt = conn.prepareStatement("INSERT INTO machine VALUES (?, ?, ?, ?, ?)");
				for(int i = 0; i < numMachines; i++) {
					State state1 = State.values()[random.nextInt(State.values().length)];
					State state2 = State.values()[random.nextInt(State.values().length)];
					State state3 = State.values()[random.nextInt(State.values().length)];
					psmt.setInt(1, i);
					psmt.setString(2, generateRandomIP());
					psmt.setString(3, state1.name());
					psmt.setString(4, state2.name());
					psmt.setString(5, state3.name());
					psmt.executeUpdate();
					conn.commit();
				}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private static void GenerateDummySoftware() {
		Random random = new Random();
		String list = "Remaining Disk Space (in GBs)";
		try {
				PreparedStatement psmt = conn.prepareStatement("INSERT INTO machinesoftware VALUES (?, ?)");
				for(int i = 0; i < 1; i++) {
					psmt.setInt(1, i);
					psmt.setString(2, list);
					psmt.executeUpdate();
					conn.commit();
				}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private static void GenerateMachineSpecs() {
		Random random = new Random();
		List<Integer> machines = new ArrayList<Integer>(numMachines);
		try {
				PreparedStatement psmt = conn.prepareStatement("INSERT INTO machinespecvalues VALUES (?, ?, ?)");
				for(int i = 0; i < numMachines; i++) {
					psmt.setInt(1, 0);
					int temp;
					do {
						temp = random.nextInt(numMachines);
					} while(machines.contains(temp));
					machines.add(temp);
					psmt.setInt(2, temp);
					psmt.setString(3, "250");
					psmt.executeUpdate();
					conn.commit();
				}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private static void GeneratePositions() {
		try {
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO positions VALUES (?, ?)");
			for(int i = 0; i < 1; i++) {
				psmt.setInt(1, i);
				psmt.setString(2, "CEO");
				psmt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private static void GenerateUsers() {
		Random random = new Random();

		try {
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)");
			for(int i = 0; i < numUsers; i++) {
				psmt.setInt(1, i);
				psmt.setString(2, RandomStringUtils.random(random.nextInt(10) + 1, true, false));
				psmt.setString(3, RandomStringUtils.random(random.nextInt(10) + 1, true, false));
				psmt.setString(4, RandomStringUtils.random(random.nextInt(10) + 1, true, true));
				psmt.setString(5, RandomStringUtils.random(random.nextInt(10) + 1, true, true));
				psmt.setString(6, RandomStringUtils.random(random.nextInt(10) + 1, true, true));
				psmt.setInt(7, 0);
				psmt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private static void GenerateRooms() {
		List<Integer> machines = new ArrayList<Integer>(numMachines);
		Random random = new Random();
		try {
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO room VALUES (?, ?, ?, ?)");
			for(int i = 0; i < numMachines; i++) {
				psmt.setInt(1, i);
				psmt.setString(2, RandomStringUtils.random((int)(Math.random() * 5) + 5, true, false));
				psmt.setString(3, "2014-12-07 03:16:00");
				int temp;
				do {
					temp = random.nextInt(numMachines);
				} while(machines.contains(temp));
				machines.add(temp);
				psmt.setInt(4, temp);
				roomMachine.put(i, temp);
				psmt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	private static void GenerateVideos() {
		Random random = new Random();
		try {
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO video VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			for(int i = 0; i < numUsers * 10; i++) {
				psmt.setInt(1, i);
				psmt.setString(2, RandomStringUtils.random(15, true, true));
				psmt.setString(3, "2014-12-07");
				psmt.setString(4, "2014-12-07");
				psmt.setString(5, RandomStringUtils.random(15, true, true));
				psmt.setString(6, RandomStringUtils.random(15, true, true));
				int temp = random.nextInt(numMachines);
				psmt.setDouble(7, temp);
				psmt.setDouble(8, (int)(Math.random() * 1024));
				psmt.setDouble(9, (int)(Math.random() * 1024));
				psmt.setDouble(10, roomMachine.get(temp));

				psmt.executeUpdate();
				conn.commit();
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
