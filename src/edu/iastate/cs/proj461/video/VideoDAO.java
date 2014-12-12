package edu.iastate.cs.proj461.video;

import java.util.List;

public interface VideoDAO {

	public List<Video> findVideoByCapturedDateTime(String datetime);
	public List<Video> findVideoByCapturedDateTimeRange(String datetime, long range);
	public List<Video> findVideoByCapturedDateTimeAndRoom(String datetime, int roomID);
	public List<Video> findAllVideos();
	public void addVideo(Video video);
}
