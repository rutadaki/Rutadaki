package org.fewnuts.rutadaki.persistence.dao;

import java.util.List;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.PuntualEvent;

/**
 * Dao for {@link PuntualEvent}
 * 
 * @author Fernando
 *
 */
public interface PuntualEventDao extends GenericDao<PuntualEvent, Long>{
	
	/**
	 * Gets the event list in a day
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public List<PuntualEvent> getPuntualEventsByDay(int day, int month, int year);
	
	/**
	 * In a month
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public List<PuntualEvent> getPuntualEventsByMonth(int month, int year);

	public List<PuntualEvent> getOnComingEventsByCategory(Category category);
	
	public List<PuntualEvent> getAll();
	
	public List<PuntualEvent> searchByText(String text_search);
}
