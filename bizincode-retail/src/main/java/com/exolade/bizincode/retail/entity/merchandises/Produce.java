package com.exolade.bizincode.retail.entity.merchandises;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Produce extends Merchandise{
	private Date sell_by;

	public Date getSell_by() {
		return sell_by;
	}

	public void setSell_by(Date sell_by) {
		this.sell_by = sell_by;
	}
	
}
