/**
 * This interface specify what each dao is allowed to do when connected to the DB.
 */
package com.exolade.bizincode.retail.dao;

import java.util.List;


public interface AbstractDao {
	void updateAll();
	boolean save(List<Object> t);
	List<Object> get(String query, List<String> tag, List<Object> t);
	boolean find(String query, List<String> tag, List<Object> t);
	int deleteSelected(String query, List<String> tag, List<Object> t);
	boolean deleteItem(List<Object> t);
	void closeDao();
	void rollback();
}
