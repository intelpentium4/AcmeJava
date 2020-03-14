package com.acme.domain;

/**
 * This class provides the common fields for all services.
 * @author Pi
 */
public class Service implements Product{
	private String name;
	private int estimatedTaskDuration; 
	private boolean timeAndMaterials;

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	public void setEstimatedTaskDuration(int dur) { this.estimatedTaskDuration = dur; }
	public int getEstimatedTaskDuration() { return this.estimatedTaskDuration; }
	public void setTimeAndMaterials(boolean tAndM) { this.timeAndMaterials = tAndM; }
	public boolean getTimeAndMaterials() { return this.timeAndMaterials; }

	public Service(String n, int dur, boolean tAndM) {
		this.estimatedTaskDuration = dur;
		this.name = n;
		this.timeAndMaterials = tAndM;
	}

	public String toString() {return name + "(a " + estimatedTaskDuration + " day service)";}
}