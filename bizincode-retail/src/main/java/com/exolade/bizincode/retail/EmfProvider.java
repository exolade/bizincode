package com.exolade.bizincode.retail;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfProvider {
	private static final String APP_P_UNIT = "bizincode-retail";
	private static final String TEST_P_UNIT = "bizincode-retail-test";
	private static final EmfProvider provider = new EmfProvider();
	private static boolean test = false;
	private EntityManagerFactory emf;

	public EmfProvider getInstanceOf() {
		return provider;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if(emf == null && !test) {
			emf = Persistence.createEntityManagerFactory(APP_P_UNIT);
		} else if (emf == null && test) {
			emf = Persistence.createEntityManagerFactory(TEST_P_UNIT);
		}
		return emf;
	}
	
	public void setTest(boolean test) {
		this.test = test;
	}

	public void closeEmf() {
		if(emf.isOpen() || emf != null) {
			emf.close();
		}
		emf = null;
	}
}
