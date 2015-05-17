package com.exolade.bizincode.retail.entity.merchandises;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "Product")
@NamedQueries({
    @NamedQuery(name="Product.findAll",
                query="SELECT p FROM Product p"),
    @NamedQuery(name="Product.findByName",
    			query="SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name="Product.findByManuDate",
    			query="SELECT p FROM Product p WHERE p.manufacture_date = :manufacture_date"),
})
public class Product extends Merchandise {
	private String name;
	private double price;
	private String description;
	private Date manufacture_date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getManufacture_date() {
		return manufacture_date;
	}

	public void setManufacture_date(Date manufacture_date) {
		this.manufacture_date = manufacture_date;
	}
	
}
