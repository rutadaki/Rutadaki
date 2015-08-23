package org.fewnuts.rutadaki.persistence.dao;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.PuntualEvent;
import org.fewnuts.rutadaki.domain.StoredLocation;

public interface DaoFactory {
	
	/**
	 * Creates a dao for {@link Category}
	 * 
	 * @return
	 */
	CategoryDao createCategoryDao();
	
	/**
	 * Creates a dao for {@link PuntualEvent}
	 * 
	 * @return
	 */
	PuntualEventDao createPuntualEventDao();
	
	/**
	 * Creates a dao for {@link LongEvent}
	 * 
	 * @return
	 */
	LongEventDao createLongEventDao();
	
	/**
	 * Creates a dao for {@link StoredLocation}
	 * 
	 * @return
	 */
	StoredLocationDao createStoredLocationDao();
	
	EventDao createEventDao();
	
	CityDao createCityDao();
	
	EventPromotionDao createEventPromotionDao();
	
	/**
	 * Creates a new Connection Context for Persistence.
	 */
	public void beginConectionScope();

	/**
	 * Ends the current connection context.
	 */
	public void endConectionScope();

	/**
	 * Begins a transaction
	 */
	public void beginTransaction();

	/**
	 * Commit the current transaction
	 */
	public void commitTransaction();

	

	

	
}
