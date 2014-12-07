package edu.iastate.cs.proj461.test;

import java.util.Date;
import java.util.List;

import edu.iastate.cs.proj461.util.HibernateUtil;
import edu.iastate.cs.proj461.video.Video;
import edu.iastate.cs.proj461.video.VideoDAO;
import edu.iastate.cs.proj461.video.VideoDAOImpl;

public class HibernateTest {
		
	public static void main(String[] args) {
		VideoDAO videoDAO = new VideoDAOImpl(HibernateUtil.getSessionFactory());
		
		Date date = new Date();
		
		List<Video> resultList = (List<Video>) videoDAO.findVideoByCapturedDateTime("2014-12-07", 
				true);
	}

	//Fetch data from database here
	//	Cast to corresponding class


}
