package com.acme.domain;

/**
 * This class provides the common fields for solid goods
 * @author Pi
 */
public class Solid extends Good {
	private double width;
	private double length;

	public void setWidth(double width) { this.width = width; }
	public double getWidth() { return this.width; }
	public void setLength(double length) { this.length = length; }
	public double getLength() { return this.length; }

	public Solid(String name, int modelNumber, double height,
			UnitOfMeasureType uoM, boolean flammable, double wgtPerUofM,
			double width, double length) {
		super(name, modelNumber, height, uoM, flammable, wgtPerUofM);
		this.width = width;
		this.length = length;
	}
	
	public double volume() {
		return width * length * super.getHeight();
	}
	public String toString() {
		return super.toString() + " that is " + volume() + " " + getUnitOfMeasure() + " in size";
	}
}