package edu.iastate.cs.proj461.video;

import edu.iastate.cs.proj461.machine.Machine;
import edu.iastate.cs.proj461.room.Room;

public class Video {
	
	private Room roomLocation;
	private int videoID;
	private double size;
	private String length;
	private String CapturedVideoName;
	private String CapturedDateTime;
	private String DateAnalysisDone;
	private String AnalysisDirName;
	private String UploadedFileName;
	private Machine machine;
	
	public Video() {
		
	}
	
	public Video(Room roomRecorded, int videoID, double size, String length, String CapturedVideoName,
			String CapturedDateTime, String DateAnalysisDone,
			String AnalysisDirName, String UploadedFileName, Machine machine) {
		this.roomLocation = roomRecorded;
		this.videoID = videoID;
		this.size = size;
		this.length = length;
		this.CapturedVideoName = CapturedVideoName;
		this.CapturedDateTime = CapturedDateTime;
		this.DateAnalysisDone = DateAnalysisDone;
		this.AnalysisDirName = AnalysisDirName;
		this.UploadedFileName = UploadedFileName;
		this.machine = machine;
	}
	
	public Video(Room roomRecorded, int videoID, double size, String length, String CapturedVideoName, String CapturedDateTime, 
			Machine machine) {
		this(roomRecorded, videoID, size, length, CapturedVideoName, CapturedDateTime, null, null, null, machine);
	}
	
	public Room getRoom() {
		return roomLocation;
	}
	public void setRoomID(Room roomRecorded) {
		this.roomLocation = roomRecorded;
	}
	public int getVideoID() {
		return videoID;
	}
	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getCapturedVideoName() {
		return CapturedVideoName;
	}
	public void setCapturedVideoName(String capturedVideoName) {
		this.CapturedVideoName = capturedVideoName;
	}
	public String getCapturedDateTime() {
		return CapturedDateTime;
	}
	public void setCapturedDateTime(String capturedDateTime) {
		this.CapturedDateTime = capturedDateTime;
	}
	public String getDateAnalysisDone() {
		return DateAnalysisDone;
	}
	public void setDateAnalysisDone(String dateAnalysisDone) {
		this.DateAnalysisDone = dateAnalysisDone;
	}
	public String getAnalysisDirName() {
		return AnalysisDirName;
	}
	public void setAnalysisDirName(String analysisDirName) {
		this.AnalysisDirName = analysisDirName;
	}
	public String getUploadedFileName() {
		return UploadedFileName;
	}
	public void setUploadedFileName(String uploadedFileName) {
		this.UploadedFileName = uploadedFileName;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachineIP(Machine machine) {
		this.machine = machine;
	}

}
