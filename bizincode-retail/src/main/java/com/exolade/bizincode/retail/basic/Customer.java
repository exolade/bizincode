/**
 * Representation of a customer that visited the store.
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.basic;

import com.exolade.bizincode.retail.PurchaseProfile;

public class Customer extends Person{
	//collects purchase preferences of a customer and 'profiles' according to age and gender
	private PurchaseProfile profile;
	
	public PurchaseProfile getProfile() {
		return profile;
	}
	
	public void updateProfile(final PurchaseProfile newProfile) {
		profile = newProfile;
	}
	
} //class
