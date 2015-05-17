/**
 * Each customer (after initial purchase) will be fitted with a 'profile' that determines
 * their purchase preference depending on gender, age, and time of purchase.
 * 
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.exolade.bizincode.retail.entity.Customer;

@Entity
@Table(name = "Purchase_Profile")
@NamedQueries({
    @NamedQuery(name="Purchase_Profile.findAll",
                query="SELECT p FROM PurchaseHistory p"),
})
public class PurchaseHistory {
	
	private int customer_id;
	private Customer customer;
	
	
	@Id @GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", 
					  parameters = @Parameter(name = "property", value = "customer"))
	public int getCustomerId() {
		return customer_id;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomerId(int id) {
		this.customer_id = id;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void generate() {
		
		
	}
}
