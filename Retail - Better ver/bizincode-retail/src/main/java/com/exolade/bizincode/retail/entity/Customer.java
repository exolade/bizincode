/**
 * Representation of a customer that visited the store.
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer{
	
	private int id;
	private int age;
	private int phone_num;
	private String name;
	private String address;
	private char gender;
	private PurchaseProfile profile;
	
	public Customer() { }
	
	public Customer(final PurchaseProfile prof, final int age, final int phone, 
					final String name, final String add, final char gender) {
		this.profile = prof;
		this.age = age;
		this.phone_num = phone;
		this.name = name;
		this.address = add;
		this.gender = gender;
	}
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	public PurchaseProfile getProfile() {
		return profile;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID")
	public int getId() {
		return id;
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
	
	public void setGender(char gender) {
		this.gender = gender;
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
		this.id = id;
	}
	
	public void setProfile(final PurchaseProfile newProfile) {
		profile = newProfile;
	}
	
} //class
