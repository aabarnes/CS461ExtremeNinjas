package edu.iastate.cs.proj461.machine;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class MachineSpecValuePK implements Serializable{
	
	private static final long serialVersionUID = 4130378088016241791L;
	
	private Machine machine;
	private MachineSoftware code;
	
	public MachineSpecValuePK() {
		
	}
	
	public MachineSpecValuePK(Machine machine, MachineSoftware software) {
		this.machine = machine;
		this.code = software;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public MachineSoftware getCode() {
		return code;
	}

	public void setCode(MachineSoftware code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof  MachineSpecValue))
			return false;
		if(obj == this)
			return true;
		
		MachineSpecValuePK other = (MachineSpecValuePK) obj;
		return new EqualsBuilder().
				append(machine, other.getMachine()).
				append(code, other.getCode()).
				isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).
				append(machine.hashCode()).
				append(code.hashCode()).
				toHashCode();
	}

}
