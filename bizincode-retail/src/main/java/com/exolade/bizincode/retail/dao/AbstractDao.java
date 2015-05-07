/**
 * This interface specify what each dao is allowed to do when connected to the DB.
 */
package com.exolade.bizincode.retail.dao;

import java.util.List;


public interface AbstractDao {
	/**
	 * Uses flush to update all data in the DB (JPA).
	 */
	void refreshDB();
	
	/**
	 * Saves entry/entries into DB. Batch-storing is also considered.
	 * @param list is the entities to be save to the database.
	 */
	boolean save(List<Object> t);
	
	List<Object> getAll();
	
	Object getById(int id);
	
	/**
	 * Finds and return results from query (single or multiple result are all included in list).
	 * @param query is a custom query passed from client class 
	 * 		  (ie. "SELECT u FROM User u WHERE u.Login = :login").
	 * @param tag is the string associate with specific conditions in the query (ie. :login).
	 * @param condition is the object specifying conditions in the query (ie. :login).
	 * @return resulting entry/entries matching query specification.
	 */
	List<Object> getByQuery(String query, List<String> tag, List<Object> t);
	
	boolean findById(int id);
	
	/**
	 * Finds an entry in the DB with a custom query (may find multiple occurrences, but will
	 * only take into account whether an entry is found or not).
	 * @param query is a custom query passed from client class 
	 * 		  (ie. "SELECT u FROM User u WHERE u.Login = :login").
	 * @param tag is the string associate with specific conditions in the query (ie. :login).
	 * @param condition is the object specifying conditions in the query (ie. :login).
	 * @return whether an entry (or multiple) has been found via the query.
	 */
	boolean findByQuery(String query, List<String> tag, List<Object> t);
	
	int deleteById(int id);
	
	/**
	 * Finds an entry in the DB with a custom query and remove that entry from DB.
	 * @param query is a custom query passed from client class 
	 * 		  (ie. "SELECT u FROM User u WHERE u.Login = :login").
	 * @param tag is the string associate with specific conditions in the query (ie. :login).
	 * @param condition is the object specifying conditions in the query (ie. :login).
	 * @return whether an entry (or multiple) has been found via the query.
	 */
	int deleteByQuery(String query, List<String> tag, List<Object> t);
	
	void updateThis(Object o);
	
	void closeDao();
	
	/**
	 * Only for testing purposes! Do not use outside of JUnit!
	 */
	void rollback();
}
