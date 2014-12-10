package edu.iastate.cs.proj461;

import java.util.List;

public class DatatableObject {
	
	List<?> data;
	
	public DatatableObject() {
		
	}

	public void setData(List<?> resultList) {
		this.data = resultList;
	}
	
	public List<?> getData() {
		return data;
	}

}
