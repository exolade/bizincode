/**
 * More personal details of an employee. Mapping EM_ID with Employee.class
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "EmployeeDetail")
@NamedQueries({
    @NamedQuery(name="EmployeeDetail.findAll",
                query="SELECT d FROM EmployeeDetail d"),
    @NamedQuery(name="EmployeeDetail.findById",
                query="SELECT d FROM EmployeeDetail d WHERE d.id = :id"),
})
public class EmployeeDetail{
	
	private int em_id;
	private Employee employee;
	private int age;
	private int phone_num;
	private String name;
	private String address;
	private char gender;
	
	public EmployeeDetail() { }
	
	public EmployeeDetail(final int age, final int phone, final String name, 
						  final String add, final char gender) {
		this.age = age;
		this.phone_num = phone;
		this.name = name;
		this.address = add;
		this.gender = gender;
	}
	
	@Id @GeneratedValue(generator = "generator")
	@Column(name = "EM_ID")
	@GenericGenerator(name = "generator", strategy = "foreign", 
					  parameters = @Parameter(name = "property", value = "employee"))
	public int getId() {
		return em_id;
	}

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "EM_ID")
	public Employee getEmployee() {
		return employee;
	}
	
	@Column(name = "GENDER", nullable = false)
	public char getGender() {
		return gender;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}
	
	@Column(name ="ADDRESS", nullable = false)
	public String getAddress() {
		return address;
	}
	
	@Column(name = "AGE", nullable = false)
	public int getAge() {
		return age;
	}
	
	@Column(name = "PHONE", nullable = false)
	public int getPhoneNum() {
		return phone_num;
	}
	
	public void setName(final String newName) {
		name = newName;
	}
	
	public void setAddress(final String newAdd) {
		address = newAdd;
	}
	
	public void setAge(final int newAge) {
		age = newAge;
	}
	
	public void setPhoneNum(final int newPhone) {
		phone_num = newPhone;
	}

	public void setId(int id) {
		this.em_id = id;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
} //class
