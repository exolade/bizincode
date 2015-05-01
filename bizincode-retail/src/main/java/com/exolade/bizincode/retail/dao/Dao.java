/**
 * This interface specify what each dao is allowed to do when connected to the DB.
 */
package com.exolade.bizincode.retail.dao;

import java.util.List;

import com.exolade.bizincode.retail.entity.Employee;

public interface Dao {
	boolean save(List<Object> t);
	List<Object> get(String query, List<String> tag, List<Object> t);
	boolean find(String query, List<String> tag, List<Object> t);
	boolean delete(List<Object> t);
	void closeDao();
}
