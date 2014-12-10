package edu.iastate.cs.proj461.video;

import java.util.List;

public interface VideoDAO {

	List<Video> findVideoByCapturedDateTime(String datetime);
	List<Video> findVideoByCapturedDateTimeRange(String datetime, long range);
	List<Video> findVideoByCapturedDateTimeAndRoom(String datetime, int roomID);
	public List<Video> findAllVideos();
}
