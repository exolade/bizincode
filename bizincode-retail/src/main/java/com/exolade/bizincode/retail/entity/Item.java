/**
 * Representation of a single merchandise
 * @version 1.0-SNAPSHOT
 * @author Exolade
 *//*
package com.exolade.bizincode.retail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.exolade.bizincode.retail.misc.MerchandiseType;


@Entity
@Table(name = "Merchandise")
@NamedQueries({
    @NamedQuery(name="Merchandise.findAll",
                query="SELECT m FROM Item m"),
    @NamedQuery(name="Merchandise.findById",
                query="SELECT m FROM Item m WHERE m.id = :id"),
    @NamedQuery(name="Merchandise.findByName",
                query="SELECT m FROM Item m WHERE m.name = :name"),
    @NamedQuery(name="Merchandise.findByType",
                query="SELECT m FROM Item m WHERE m.type = :type"),
})
public class Item extends Merchandise{
	private String name;
	private double price;
	private MerchandiseType type;
	
	public Item() { }
	
	public Item(String name, double price, MerchandiseType type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	
	@Column(name = "PRICE")
	public double getPrice() {
		return price;
	}
	
	@Column(name = "ITEM_TYPE")
	@Enumerated(EnumType.STRING)
	public MerchandiseType getType() {
		return type;
	}
	
	public void setType(MerchandiseType type) {
		this.type = type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
} //class
*/