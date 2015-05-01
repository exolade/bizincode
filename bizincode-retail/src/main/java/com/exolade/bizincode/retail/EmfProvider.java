package com.exolade.bizincode.retail;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfProvider {
	private static final String PERSISTENT_UNIT = "bizincode-retail";
	private static final EmfProvider provider = new EmfProvider();
	private EntityManagerFactory emf;

	public EmfProvider getInstanceOf() {
		return provider;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
		}
		return emf;
	}

	public void closeEmf() {
		if(emf.isOpen() || emf != null) {
			emf.close();
		}
		emf = null;
	}
}
