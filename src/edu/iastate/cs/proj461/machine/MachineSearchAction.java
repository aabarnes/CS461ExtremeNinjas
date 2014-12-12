package edu.iastate.cs.proj461.machine;

import java.util.List;
import com.opensymphony.xwork2.Action;
import edu.iastate.cs.proj461.DatatableObject;
import edu.iastate.cs.proj461.util.HibernateUtil;

public class MachineSearchAction {
	
	private DatatableObject returnObj;

	public String getMachineIps() {
		
		List<String> ips;
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());
		
		ips = machineDAO.getMachineIps();
		System.out.println(ips);
		
		returnObj = new DatatableObject();
		returnObj.setData(ips);
		
		return Action.SUCCESS;
	}

	public DatatableObject getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(DatatableObject returnObj) {
		this.returnObj = returnObj;
	}
}
