package edu.iastate.cs.proj461;

import java.util.List;

import edu.iastate.cs.proj461.video.DatatableObject;

public class DummyClassDatatableObject extends DatatableObject{

	int iTotalRecords;

	int iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<DummyClass> aaData;

	public List<DummyClass> getData() {
		return aaData;
	}

	@Override
	public void setData(List<?> resultList) {
		this.aaData = (List<DummyClass>) resultList;
	}

}