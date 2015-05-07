/**
 * Representation of each staff working in the store.
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.entity;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




import com.exolade.bizincode.retail.misc.EmployeeType;
import com.exolade.bizincode.retail.misc.EmployeePosition;

@Entity
@Table(name = "Employee")
@NamedQueries({
    @NamedQuery(name="Employee.findAll",
                query="SELECT e FROM Employee e"),
    @NamedQuery(name="Employee.findById",
                query="SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name="Employee.findByPosition",
                query="SELECT e FROM Employee e WHERE e.position = :position"),
})
public class Employee{

	private int em_id;
	private double salary;
	private int hours;
	private EmployeeType type;
	private EmployeePosition position;
	private Date start_date;
	private Date end_date;
	private Employee manager;
	private EmployeeDetail detail;
	private Set<Employee> subordinates = new HashSet<Employee>();

	public Employee() { }

	public Employee(final double salary, final int hour, final EmployeeType type,
			/*final Date startD, final Date endD,*/ final Employee mnger, 
			final EmployeePosition pos) {
		this.salary = salary;
		this.hours = hour;
		this.type = type;
		//this.start_date = startD;
		//this.end_date = endD;
		this.manager = mnger;
		this.position = pos;
	}


	@PrePersist
	protected void onCreate() {
		start_date = new Date();
	}
	
	@OneToOne(mappedBy = "employee", cascade = {CascadeType.ALL}, orphanRemoval=true)
	//@JoinColumn(name = "em_detail")
	public EmployeeDetail getDetail() {
		return detail;
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

	@Column(name = "EM_POSITION")
	@Enumerated(EnumType.STRING)
	public EmployeePosition getPosition() {
		return position;
	}

	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return start_date;
	}

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
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

	public void setEType(final EmployeeType newET) {
		type = newET;
	}

	public void setPosition(final EmployeePosition newPos) {
		position = newPos;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}

	public void setId(int id) {
		this.em_id = id;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public void setDetail(EmployeeDetail detail) {
		this.detail = detail;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	
	private void setStartDate(Date startDate) {
		this.start_date = startDate;
	}
	
	public void add1Hour() {
		hours++;
	}
	
	public void addSubordinate(Employee sub) {
		subordinates.add(sub);
	}
	
	public void removeSubordinate(Employee sub) {
		subordinates.remove(sub);
	}
} //class
