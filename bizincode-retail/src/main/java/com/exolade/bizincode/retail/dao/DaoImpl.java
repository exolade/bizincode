/**
 * This class will perform CRUD operations for Customer.class using EntityManagerFactory that
 * is being passed in at initialization. 
 * 
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.exolade.bizincode.retail.EmfProvider;
import com.exolade.bizincode.retail.entity.Customer;

public class DaoImpl implements Dao{
	private EntityManagerFactory emf;
	@PersistenceContext(unitName = "bizincode-retail")
	private EntityManager em;

	public DaoImpl(EmfProvider provider) {
		try {
			emf = provider.getEntityManagerFactory();
			em = emf.createEntityManager();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves entry/entries into DB. Batch-storing is also considered.
	 * @param list is the entities to be save to the database.
	 */
	@Override
	public boolean save(List<Object> list) {
		boolean saved = true;
		if (list.isEmpty()) {
			return !saved;
		}
		try {
			em.getTransaction().begin();
			for (int i = 0; i < list.size(); i++) {
				em.persist(list.get(i));
				if (i != 0 && i % 10000 == 0) {
					//to save memory and speed when dealing with batch saving
					em.getTransaction().commit();
					em.clear();
					em.getTransaction().begin();
				}
			}
			em.getTransaction().commit();
			
		} catch (PersistenceException e) { 
			if (em.getTransaction().isActive()) {
				saved = !saved;
				System.out.println("Saving encountered error!");
				e.printStackTrace();
				em.getTransaction().rollback();
			}
		}
		return saved;
	}

	/**
	 * Finds and return results from query (single or multiple result are all included in list).
	 * @param query is a custom query passed from client class 
	 * 		  (ie. "SELECT u FROM User u WHERE u.Login = :login").
	 * @param tag is the string associate with specific conditions in the query (ie. :login).
	 * @param condition is the object specifying conditions in the query (ie. :login).
	 * @return resulting entry/entries matching query specification.
	 */
	@Override
	public List<Object> get(String query, List<String> tag, List<Object> condition) {
		if (tag.size() != condition.size()) {
			return null;
		}
		em.getTransaction().begin();
		Query q = em.createQuery(query);
		for (int i = 0; i < tag.size(); i++) {
			q.setParameter(tag.get(i), condition.get(i));
		}
		try {
			if (q.getResultList().size() > 0) {
				return q.getResultList();
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * Finds an entry in the DB with a custom query (may find multiple occurrences, but will
	 * only take into account whether an entry is found or not).
	 * @param query is a custom query passed from client class 
	 * 		  (ie. "SELECT u FROM User u WHERE u.Login = :login").
	 * @param tag is the string associate with specific conditions in the query (ie. :login).
	 * @param condition is the object specifying conditions in the query (ie. :login).
	 * @return whether an entry (or multiple) has been found via the query.
	 */
	@Override
	public boolean find(String query, List<String> tag, List<Object> condition) {
		boolean found = true;
		if (tag.size() != condition.size()) {
			return !found;
		}
		em.getTransaction().begin();
		Query q = em.createQuery(query);
		for (int i = 0; i < tag.size(); i++) {
			q.setParameter(tag.get(i), condition.get(i));
		}
		try {
			if (q.getResultList().size() > 0) {
				return found;
			}
		} catch (NoResultException e) {
			return !found;
		}
		return !found;
	}

	@Override
	public boolean delete(List<Object> list) {
		boolean deleted = true;
		try {
			em.getTransaction().begin();
			for (int i = 0; i < list.size(); i++) {
				em.remove(list.get(i));
			}
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				deleted = false;
			}
		} 
		finally {
			return deleted;
		}
	}

	@Override
	public void closeDao() {
		em.close();
	}
} //class