/**
 * Representation of each person, be it a staff or a customer.
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.basic;

public class Person {
	private String name;
	private String address;
	private char gender;
	private int phone_num;
	private int age;
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getAge() {
		return age;
	}
	
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
	
	
} //class
