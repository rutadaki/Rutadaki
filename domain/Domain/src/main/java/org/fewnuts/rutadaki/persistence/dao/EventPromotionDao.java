package org.fewnuts.rutadaki.persistence.dao;

import java.util.List;

import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.EventPromotion;
import org.fewnuts.rutadaki.domain.StoredLocation;

/**
 * DAO for {@link StoredLocation}
 * 
 * @author Fernando
 *
 */
public interface EventPromotionDao extends GenericDao<EventPromotion, Long>{
	
	/**
	 * Retrieves all the EventPromotions in Database
	 * 
	 * @return
	 */
	public List<EventPromotion> getAll();
	
	/**
	 * Retrieves all the EventPromotions in Database related to an event
	 * 
	 * @return
	 */
	public List<EventPromotion> getPromotionsEvent(Event e);
}
