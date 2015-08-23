package org.fewnuts.rutadaki.services;

import java.util.List;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.PuntualEvent;

public interface EventServices {
	
	List<Event> getEventsDay(int day, int month, int year);
	
	List<Event> getEventsMonth(int month, int year);
	
	void deleteEvent(Event e);
	
	void saveEvent(Event e);
	
	PuntualEvent getPuntualEvent(Long eventId);
	
	LongEvent getLongEvent(Long eventId);
	
	List<Event> getOnComingEventsByCategory(Long categoryId);
	
	List<PuntualEvent> getAllPuntualEvents();
	
	List<LongEvent> getAllLongEvents();
	
	List<Event> searchByText(String text_search);
	
	Event getEventById(Long idEvent);
	
	List<Event> getOutStandingEvents();
	
	void addPromotion(Event e);
	
	void removePromotion(Event e);
}
