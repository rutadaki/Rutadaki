package org.fewnuts.rutadaki.persistence.dao;

import java.util.List;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.domain.PuntualEvent;

/**
 * Dao for {@link City}
 * 
 * @author Fernando
 *
 */
public interface CityDao extends GenericDao<City, Long>{
	
	public List<City> getAll();

}
