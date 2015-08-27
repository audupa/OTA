package com.intuit.OTA.dao;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author Vijayan Srinivasan
 * @since Jan 3, 2015 7:54:50 PM
 *
 */
public interface GenericDAO<T> {

	Long count();
	Long countBy(String columnName, Object value);
	Long countBy(Map<String, Object> params);

	T create(T t);
	
	void deleteById(Long id);
	void deleteByIds(List<Long> ids);
	
	T updateById(T t);
	
	T findById(Long id);
	T findBy(String columnName, Object value);
	T findBy(Map<String, Object> params);
	
	List<T> findManyBy(Map<String, Object> params);
	List<T> findManyBy(String columnName, Object value);
	
}