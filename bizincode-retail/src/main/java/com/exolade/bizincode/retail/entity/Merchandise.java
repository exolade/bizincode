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

import com.exolade.bizincode.retail.type.ItemType;

@Entity
@Table(name = "Merchandise")
public class Merchandise{
	private int id;
	private String name;
	private double price;
	private ItemType type;
	//number of merchandise available for sell
	private int quantity;
	
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
	public ItemType getType() {
		return type;
	}
	
	public void setType(ItemType type) {
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
