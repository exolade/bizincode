/**
 * This class will perform CRUD operations for Employee.class using EntityManagerFactory that
 * is being passed in at initialization. 
 * 
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;



import com.exolade.bizincode.retail.EmfProvider;
import com.exolade.bizincode.retail.entity.Employee;

public class EmployeeDaoImpl implements AbstractDao{
	private EntityManagerFactory emf;
	private EntityManager em;

	public EmployeeDaoImpl(EmfProvider provider) {
		try {
			emf = provider.getEntityManagerFactory();
			em = emf.createEntityManager();
			em.getTransaction().begin();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAll() {
		em.getTransaction().begin();
		em.flush();
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
		boolean result = false;
		if (tag.size() != condition.size()) {
			return result;
		}
		Query q = em.createQuery(query);
		for (int i = 0; i < tag.size(); i++) {
			q.setParameter(tag.get(i), condition.get(i));
		}
		try {
			if (q.getResultList().size() > 0) {
				result = true;
			}
		} catch (NoResultException e) {
			return result;
		}
		return result;
	}

	@Override
	public boolean deleteItem(List<Object> list) {
		//em.getTransaction().begin();
		boolean deleted = true;
		try {
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
	public int deleteSelected(String query, List<String> tag, List<Object> condition) {
		em.getTransaction().begin();
		List<Object> result = get(query, tag, condition);
		if (result == null || result.size() == 0) {
			System.out.println("can't find anything to delete");
			return 0;
		}
		for (int i = 0; i < result.size(); i++) {
			Employee tmp = (Employee) result.get(i);
			Set<Employee> tmpSub = tmp.getSubordinates();
			tmp.setDetail(null);
			tmp.setManager(null);
			for (Employee sub : tmpSub) {
				sub.setManager(null);
			}
			//tmp.setSubordinates(null);
			em.remove(tmp);
		}
		em.getTransaction().commit();
		return result.size();
	}
	
	@Override
	public void rollback() {
		em.getTransaction().begin();
		em.getTransaction().rollback();
	}

	@Override
	public void closeDao() {
		em.close();
	}
} //class