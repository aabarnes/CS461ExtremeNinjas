package edu.iastate.cs.proj461;

import java.util.List;

public interface VideoDAO {

	List<Video> findVideoByCapturedDateTime(long datetime, boolean searchEntireDay);
	List<Video> findVideoByCapturedDateTime(long datetime);
	List<Video> findVideoByCapturedDateTimeRange(long datetime, long range);
}
