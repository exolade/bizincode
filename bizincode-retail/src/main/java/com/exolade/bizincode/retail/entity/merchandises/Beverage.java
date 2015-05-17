package com.exolade.bizincode.retail.entity.merchandises;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "Beverage")
@NamedQueries({
    @NamedQuery(name="Beverage.findAll",
                query="SELECT b FROM Beverage b"),
    @NamedQuery(name="Beverage.findByName",
    			query="SELECT b FROM Beverage b WHERE b.name = :name"),
    @NamedQuery(name="Beverage.findBySellBy",
    			query="SELECT b FROM Beverage b WHERE b.sell_by = :sell_by"),
})
public class Beverage extends Produce {
	private String name;
	private boolean alcoholic;
	private double price;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAlcoholic() {
		return alcoholic;
	}
	public void setAlcoholic(boolean alcoholic) {
		this.alcoholic = alcoholic;
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
}
