package org.fewnuts.rutadaki.persistence.dao;

import java.util.List;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.LongEvent;

/**
 * DAO for {@link LongEvent}
 * 
 * @author Fernando
 *
 */
public interface LongEventDao extends GenericDao<LongEvent, Long>{
	
	/**
	 * Gets the event list in a day
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public List<LongEvent> getLongEventsByDay(int day, int month, int year);
	
	/**
	 * In a month
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public List<LongEvent> getLongEventsByMonth(int month, int year);

	public List<LongEvent> getOnComingEventsByCategory(Category category);
	
	List<LongEvent> getAll();
	
	public List<LongEvent> searchByText(String text_search);
}
