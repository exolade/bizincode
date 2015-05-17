package com.exolade.bizincode.retail.entity.merchandises;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "Book")
@NamedQueries({
    @NamedQuery(name="Book.findAll",
                query="SELECT b FROM Book b"),
    @NamedQuery(name="Book.findByName",
    			query="SELECT b FROM Book b WHERE b.name = :name"),
    @NamedQuery(name="Book.findByAuthor",
    			query="SELECT b FROM Book b WHERE b.author = :author"),
})
public class Book extends PrintedProduct {
	private String name;
	private String author;
	private double price;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
