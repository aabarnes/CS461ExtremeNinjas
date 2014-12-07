package edu.iastate.cs.proj461.video;

import java.util.List;

public interface VideoDAO {

	List<Video> findVideoByCapturedDateTime(String datetime, boolean searchEntireDay);
	List<Video> findVideoByCapturedDateTime(String datetime);
	List<Video> findVideoByCapturedDateTimeRange(String datetime, long range);
}
