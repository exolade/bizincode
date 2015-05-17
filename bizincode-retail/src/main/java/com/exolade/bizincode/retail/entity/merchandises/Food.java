package com.exolade.bizincode.retail.entity.merchandises;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "Food")
@NamedQueries({
    @NamedQuery(name="Food.findAll",
                query="SELECT f FROM Food f"),
    @NamedQuery(name="Food.findByName",
    			query="SELECT f FROM Food f WHERE f.name = :name"),
    @NamedQuery(name="Food.findBySellBy",
    			query="SELECT f FROM Food f WHERE f.sell_by = :sell_by"),
})
public class Food extends Produce {
	private String name;
	private String description;
	private double price;
	private Date best_before;

	public Date getBest_before() {
		return best_before;
	}

	public void setBest_before(Date best_before) {
		this.best_before = best_before;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
