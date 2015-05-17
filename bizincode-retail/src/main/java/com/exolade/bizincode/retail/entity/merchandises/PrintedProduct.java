package com.exolade.bizincode.retail.entity.merchandises;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PrintedProduct extends Merchandise {
	private String publisher;
	private Date publish_date;
	private Date sell_by;
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getManufacture_date() {
		return publish_date;
	}
	public void setManufacture_date(Date manufacture_date) {
		this.publish_date = manufacture_date;
	}
	public Date getSell_by() {
		return sell_by;
	}
	public void setSell_by(Date sell_by) {
		this.sell_by = sell_by;
	}
}
