package edu.iastate.cs.proj461.disk;

public class Disk {
	
	private DiskPK diskPK; /* composite key defined in DiskPK - Machine and diskID */
	private double percentFull;
	private int capacity;
	
	public Disk(DiskPK diskPK, double percentFull, int capacity){
		this.diskPK = diskPK;
		this.percentFull = percentFull;
		this.capacity = capacity;
	}
	
	public Disk(){
		
	}

	public DiskPK getDiskPK() {
		return diskPK;
	}

	public void setDiskPK(DiskPK diskPK) {
		this.diskPK = diskPK;
	}

	public double getPercentFull() {
		return percentFull;
	}

	public void setPercentFull(double percentFull) {
		this.percentFull = percentFull;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getDiskId(){
		return this.diskPK.getDiskId();
	}
	
	public void setDiskId(int diskId){
		diskPK.setDiskId(diskId);
	}

}
