/**
 * Representation of each staff working in the store.
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.exolade.bizincode.retail.type.EmployeeType;
import com.exolade.bizincode.retail.type.Position;

@Entity
@Table(name = "Employee")
public class Employee{

	private int em_id;
	private double salary;
	private int hours;
	private EmployeeType type;
	private Date start_date;
	private Date end_date;
	private Employee manager;
	private Set<Employee> subordinates = new HashSet<Employee>();

	/////////////////////////////////////work-in-process//////////////////////////////////////////
	private List<Position> position;

	public Employee() { }

	public Employee(final double salary, final int hour, final EmployeeType type,
			final Date startD, final Date endD, final Employee mnger, 
			final List<Position> pos) {
		this.salary = salary;
		this.hours = hour;
		this.type = type;
		this.start_date = startD;
		this.end_date = endD;
		this.manager = mnger;
		this.position = pos;
	}


	@PrePersist
	protected void onCreate() {
		start_date = new Date();
	}

	//Employee-Manager relationship (self-join)
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="manager_id")
	public Employee getManager() {
		return manager;
	}
	
	//Employee-Manager relationship (self-join)
	@OneToMany(mappedBy="manager")
	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	@Column(name = "EM_TYPE")
	@Enumerated(EnumType.STRING)
	public EmployeeType getEType() {
		return type;
	}

	public List<Position> getPosition() {
		return position;
	}

	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return start_date;
	}

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	public Date getEnd_date() {
		if (end_date != null) { return end_date;}
		else { return start_date; }
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EM_ID")
	public int getId() {
		return em_id;
	}

	@Column(name = "SALARY")
	public double getSalary() {
		return salary;
	}

	@Column(name = "HOURS")
	public int getHours() {
		return hours;
	}

	public void setSalary(final double newSalary) {
		salary = newSalary;
	}

	protected void setHours(final int newHours) {
		hours = newHours;
	}

	public void setEmployeeType(final EmployeeType newET) {
		type = newET;
	}

	public void setPosition(final List<Position> newPos) {
		position = newPos;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setId(int id) {
		this.em_id = id;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	
	public void add1Hour() {
		hours++;
	}
} //class
