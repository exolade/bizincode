package com.exolade.bizincode.retail.entity.merchandises;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Merchandise")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ITEM_TYPE")
@NamedQueries({
    @NamedQuery(name="Merchandise.findAll",
                query="SELECT m FROM Merchandise m"),
    @NamedQuery(name="Merchandise.findById",
                query="SELECT m FROM Merchandise m WHERE m.id = :id"),
    @NamedQuery(name="Merchandise.findBySupplier",
    			query="SELECT m FROM Merchandise m WHERE m.supplier = :supplier"),
    @NamedQuery(name="Merchandise.findByManuCountry",
    			query="SELECT m FROM Merchandise m WHERE m.manu_country = :manu_country"),
})
public abstract class Merchandise {
	private int id;
	private String manu_country;
	private String manu_company;
	private String importer;
	private String supplier;
	//number of merchandise available for sell
	private int quantity;
	private Date latest_resupply;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManu_country() {
		return manu_country;
	}
	public void setManu_country(String manu_country) {
		this.manu_country = manu_country;
	}
	public String getManu_company() {
		return manu_company;
	}
	public void setManu_company(String manu_company) {
		this.manu_company = manu_company;
	}
	public String getImporter() {
		return importer;
	}
	public void setImporter(String importer) {
		this.importer = importer;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getLatest_resupply() {
		return latest_resupply;
	}
	public void setLatest_resupply(Date latest_resupply) {
		this.latest_resupply = latest_resupply;
	}
}
