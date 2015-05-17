/**
 * This class will perform CRUD operations for Magazine.class using EntityManagerFactory that
 * is being passed in at initialization. 
 * 
 * @version 1.0-SNAPSHOT
 * @author Exolade
 */
package com.exolade.bizincode.retail.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.exolade.bizincode.retail.EmfProvider;
import com.exolade.bizincode.retail.entity.merchandises.Merchandise;

public class MagazineDaoImpl implements AbstractDao{
	private EntityManagerFactory emf;
	private EntityManager em;

	public MagazineDaoImpl(EmfProvider provider) {
		try {
			emf = provider.getEntityManagerFactory();
			em = emf.createEntityManager();
			em.getTransaction().begin();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * {@link com.exolade.bizincode.retail.dao.AbstractDao#refreshDB()}
	 */
	public void refreshDB() {
		em.getTransaction().begin();
		em.flush();
	}
	
	@Override
	/**
	 * {@link com.exolade.bizincode.retail.dao.AbstractDao#save(List<Object> t)}
	 */
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
	
	@Override
	public List<Object> getAll() {
		Query q = em.createNamedQuery("Magazine.findAll");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Object getBySellBy(Date date) {
		Query q = em.createNamedQuery("Magazine.findBySellBy");
		q.setParameter("sell_by", date);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Object getById(int id) {
		Query q = em.createNamedQuery("Merchandise.findById");
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateAnimal() {
		Query q = em.createNamedQuery("Magazine.findByCategoryAnimal");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateArt() {
		Query q = em.createNamedQuery("Magazine.findByCategoryArt");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateAuto() {
		Query q = em.createNamedQuery("Magazine.findByCategoryAutomobile");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateBusiness() {
		Query q = em.createNamedQuery("Magazine.findByCategoryBusiness");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateEntertainment() {
		Query q = em.createNamedQuery("Magazine.findByCategoryEntertainment");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateChildren() {
		Query q = em.createNamedQuery("Magazine.findByCategoryChildren");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateTech() {
		Query q = em.createNamedQuery("Magazine.findByCategoryTechnology");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateCraft() {
		Query q = em.createNamedQuery("Magazine.findByCategoryCraft");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateDesign() {
		Query q = em.createNamedQuery("Magazine.findByCategoryDesign");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateEducation() {
		Query q = em.createNamedQuery("Magazine.findByCategoryEducation");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateFashion() {
		Query q = em.createNamedQuery("Magazine.findByCategoryFashion");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateFood() {
		Query q = em.createNamedQuery("Magazine.findByCategoryFood");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateWine() {
		Query q = em.createNamedQuery("Magazine.findByCategoryWine");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateGame() {
		Query q = em.createNamedQuery("Magazine.findByCategoryGame");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateHobby() {
		Query q = em.createNamedQuery("Magazine.findByCategoryHobby");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateHealth() {
		Query q = em.createNamedQuery("Magazine.findByCategoryHealth");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateHistory() {
		Query q = em.createNamedQuery("Magazine.findByCategoryHistory");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateGardening() {
		Query q = em.createNamedQuery("Magazine.findByCategoryGardening");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateSports() {
		Query q = em.createNamedQuery("Magazine.findByCategorySports");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateScience() {
		Query q = em.createNamedQuery("Magazine.findByCategoryScience");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateNature() {
		Query q = em.createNamedQuery("Magazine.findByCategoryNature");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateTravel() {
		Query q = em.createNamedQuery("Magazine.findByCategoryTravel");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByCateInternational() {
		Query q = em.createNamedQuery("Magazine.findByCategoryInternational");
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Object> getByName(String name) {
		Query q = em.createNamedQuery("Magazine.findByName");
		q.setParameter("name", name);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * {@link com.exolade.bizincode.retail.dao.AbstractDao#getByQuery(String query, List<String> tag, List<Object> t)}
	 */
	@Override
	public List<Object> getByQuery(String query, List<String> tag, List<Object> condition) {
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
	
	@Override
	public boolean findById(int id) {
		boolean found = true;
		Query q = em.createNamedQuery("Merchandise.findById");
		q.setParameter("id", id);
		try{
			if(q.getSingleResult() != null) return found;
		
		} catch (NoResultException e) {	
			found = !found;
		}
		return found;
	}

	/**
	 * {@link com.exolade.bizincode.retail.dao.AbstractDao#findByQuery(String query, List<String> tag, List<Object> t)}
	 */
	@Override
	public boolean findByQuery(String query, List<String> tag, List<Object> condition) {
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
	public int deleteById(int id) {
		em.getTransaction().begin();
		Merchandise tmp = (Merchandise) getById(id);
		if (tmp == null) {
			return 0;
		}
		em.remove(tmp);
		em.getTransaction().commit();
		return 1;
	}

	/**
	 * {@link com.exolade.bizincode.retail.dao.AbstractDao#deleteByQuery(String query, List<String> tag, List<Object> t)}
	 */
	@Override
	public int deleteByQuery(String query, List<String> tag, List<Object> condition) {
		em.getTransaction().begin();
		List<Object> result = getByQuery(query, tag, condition);
		if (result == null || result.size() == 0) {
			return 0;
		}
		for (int i = 0; i < result.size(); i++) {
			Merchandise tmp = (Merchandise) result.get(i);
			em.remove(tmp);
		}
		em.getTransaction().commit();
		return result.size();
	}
	
	@Override
	public void updateThis(Object obj) {
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}
	
	/**
	 * {@link com.exolade.bizincode.retail.dao.AbstractDao#rollback()}
	 */
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
