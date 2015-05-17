package com.exolade.bizincode.retail.entity.merchandises;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "Newspaper")
@NamedQueries({
    @NamedQuery(name="Newspaper.findAll",
                query="SELECT n FROM Newspaper n"),
    @NamedQuery(name="Newspaper.findByName",
    			query="SELECT n FROM Newspaper n WHERE n.name = :name"),
    @NamedQuery(name="Newspaper.findBySellBy",
    			query="SELECT n FROM Newspaper n WHERE n.sell_by = :sell_by"),
})
public class Newspaper extends PrintedProduct {
	private String name;
	private double price;
	
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
}
