package edu.iastate.cs.proj461.video;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.iastate.cs.proj461.machine.Machine;
import edu.iastate.cs.proj461.room.Room;

public class Video {
	
	private Room room;
	private int videoID;
	private double size;
	private double length;
	private String CapturedVideoName;
	
	//@Temporal(TemporalType.TIMESTAMP)
	private Date CapturedDateTime;
	private Date DateAnalysisDone;
	private String AnalysisDirName;
	private String UploadedFileName;
	private Machine machine;
	
	public Video() {
		
	}
	
	public Video(Room roomRecorded, int videoID, double size, double length, String CapturedVideoName,
			Date CapturedDateTime, Date DateAnalysisDone,
			String AnalysisDirName, String UploadedFileName, Machine machine) {
		this.room = roomRecorded;
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
	
	public Video(Room roomRecorded, int videoID, double size, double length, String CapturedVideoName, Date CapturedDateTime, 
			Machine machine) {
		this(roomRecorded, videoID, size, length, CapturedVideoName, CapturedDateTime, null, null, null, machine);
	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room roomRecorded) {
		this.room = roomRecorded;
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
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public String getCapturedVideoName() {
		return CapturedVideoName;
	}
	public void setCapturedVideoName(String capturedVideoName) {
		this.CapturedVideoName = capturedVideoName;
	}
	public Date getCapturedDateTime() {
		return CapturedDateTime;
	}
	public void setCapturedDateTime(Date capturedDateTime) {
		//System.out.println(capturedDateTime);
		if(capturedDateTime == null) {
			Date newDate = new Date(new Date().getTime() - TimeUnit.DAYS.toMillis((long)(Math.random() * 15)));
			System.out.println(newDate);
			this.CapturedDateTime = newDate;
		}
		else
			this.CapturedDateTime = capturedDateTime;
	}
	public Date getDateAnalysisDone() {
		return DateAnalysisDone;
	}
	public void setDateAnalysisDone(Date dateAnalysisDone) {
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
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}
