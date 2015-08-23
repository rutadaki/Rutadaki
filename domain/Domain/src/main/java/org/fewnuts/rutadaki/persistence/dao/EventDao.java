package org.fewnuts.rutadaki.persistence.dao;

import java.util.Date;
import java.util.List;

import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.PuntualEvent;

/**
 * Dao for {@link PuntualEvent}
 * 
 * @author Fernando
 *
 */
public interface EventDao extends GenericDao<Event, Long>{
	
	public List<Event> getAll();
	
	public List<Event> searchByText(String text_search);
	
	public List<Event> getPromotedEvents(Date date);
}
