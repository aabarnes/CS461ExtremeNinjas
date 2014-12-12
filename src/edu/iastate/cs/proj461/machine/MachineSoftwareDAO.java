package edu.iastate.cs.proj461.machine;

import java.util.List;

public interface MachineSoftwareDAO {
	
	public MachineSoftware getMachineSoftwareById(int id);
	public void addMachineSoftware(MachineSoftware machineSoftware);
	public List<MachineSoftware> getAllSoftwareCodes();

}
