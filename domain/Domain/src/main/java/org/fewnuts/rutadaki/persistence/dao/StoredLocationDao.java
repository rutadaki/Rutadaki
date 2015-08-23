package org.fewnuts.rutadaki.persistence.dao;

import java.util.List;

import org.fewnuts.rutadaki.domain.StoredLocation;

/**
 * DAO for {@link StoredLocation}
 * 
 * @author Fernando
 *
 */
public interface StoredLocationDao extends GenericDao<StoredLocation, Long>{
	
	/**
	 * Retrieves all the StoredLocation in Database
	 * 
	 * @return
	 */
	public List<StoredLocation> getAll();
}
