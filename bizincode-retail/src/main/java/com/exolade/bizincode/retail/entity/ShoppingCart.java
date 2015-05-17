package com.exolade.bizincode.retail.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;

public class ShoppingCart {
	
	private Date purchase_date;
	
	@PrePersist
	protected void onCreate() {
		purchase_date = new Date();
	}


	public Date getPurchase_date() {
		return purchase_date;
	}


	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	
}
