package edu.iastate.cs.proj461.machine;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import edu.iastate.cs.proj461.DatatableObject;
import edu.iastate.cs.proj461.util.HibernateUtil;

public class MachineStatusAction extends ActionSupport {
	
	private DatatableObject returnObj;
	
	public String execute() {
		
		List<Machine> machines;
		MachineDAO machineDAO = new MachineDAOImpl(HibernateUtil.getSessionFactory());
		
		machines = machineDAO.getAllMachines();
		
		returnObj = new DatatableObject();
		returnObj.setData(machines);
		
		return Action.SUCCESS;
	}
	
	@Override
	public void validate() {
		
	}

	public DatatableObject getReturnObj() {
		return returnObj;
	}
	
	public void setReturnObj(DatatableObject returnObj) {
		this.returnObj = returnObj;
	}
}
