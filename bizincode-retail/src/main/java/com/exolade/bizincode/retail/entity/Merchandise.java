/**
 * Representation of a single merchandise
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.exolade.bizincode.retail.misc.MerchandiseType;


@Entity
@Table(name = "Merchandise")
public class Merchandise{
	private int id;
	private String name;
	private double price;
	private MerchandiseType type;
	//number of merchandise available for sell
	private int quantity;
	
	public Merchandise() { }
	
	public Merchandise(String name, double price, MerchandiseType type, int num) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = num;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	public int getId() {
		return id;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	
	@Column(name = "PRICE")
	public double getPrice() {
		return price;
	}
	
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}
	
	@Column(name = "ITEM_TYPE")
	@Enumerated(EnumType.STRING)
	public MerchandiseType getType() {
		return type;
	}
	
	public void setType(MerchandiseType type) {
		this.type = type;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setId(int item_id) {
		this.id = item_id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
} //class
