package org.fewnuts.rutadaki.persistence.dao;

import java.util.List;

import org.fewnuts.rutadaki.domain.Category;

/**
 * DAO for {@link Category}
 * 
 * @author Fernando
 *
 */
public interface CategoryDao extends GenericDao<Category, Long>{
	
	/**
	 * Gets all the categories in the system
	 * 
	 * @return
	 */
	public List<Category> getAll();
	
}
