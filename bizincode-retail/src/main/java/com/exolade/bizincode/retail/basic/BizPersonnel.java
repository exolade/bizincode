/**
 * Representation of each staff working in the store.
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.basic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BizPersonnel extends Person {
	private double salary;
	private int hours;
	private EmployeeType type;
	private Calendar start_time;
	private List<Position> position;
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(final double newSalary) {
		salary = newSalary;
	}
	
	public int getHours() {
		return hours;
	}
	
	public void add1Hour() {
		hours++;
	}
	
	protected void setHours(final int newHours) {
		hours = newHours;
	}
	
	public EmployeeType getEType() {
		return type;
	}
	
	public void setEmployeeType(final EmployeeType newET) {
		type = newET;
	}
	
	public Date getStartTime() {
		return start_time.getTime();
	}
	
	public void setStartTime(final Calendar newST) {
		start_time = newST;
	}
	
	public List<Position> getPosition() {
		return position;
	}
	
	public void setPosition(final List<Position> newPos) {
		position = newPos;
	}
	
} //class
