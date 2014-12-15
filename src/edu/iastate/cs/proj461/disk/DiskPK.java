package edu.iastate.cs.proj461.disk;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import edu.iastate.cs.proj461.machine.Machine;

@Embeddable
public class DiskPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5871953693704008431L;
	private Machine machine;
	private int diskId;
	
	public DiskPK(){
		
	}
	
	public DiskPK(Machine machine, int diskId){
		this.machine = machine;
		this.diskId = diskId;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public int getDiskId() {
		return diskId;
	}

	public void setDiskId(int diskId) {
		this.diskId = diskId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof  Disk))
			return false;
		if(obj == this)
			return true;
		
		DiskPK other = (DiskPK) obj;
		return new EqualsBuilder().
				append(machine, other.getMachine()).
				append(diskId, other.getDiskId()).
				isEquals(); 
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(19, 41).
				append(machine.hashCode()).
				append(Integer.valueOf(diskId).hashCode()).
				toHashCode();	// 19 and 41 were randomly chosen prime numbers
	}
}
