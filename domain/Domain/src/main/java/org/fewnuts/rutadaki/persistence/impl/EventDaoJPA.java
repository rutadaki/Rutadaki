package org.fewnuts.rutadaki.persistence.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.EventPromotion;
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.utils.DateUtilities;
import org.fewnuts.rutadaki.persistence.dao.EventDao;

public class EventDaoJPA extends
		GenericJPADAO<Event, Long> implements
		EventDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public EventDaoJPA(EntityManager em) {
		super(em);
	}

	public List<Event> getAll() {
		List<Event> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT e FROM Event e ");

		result = new ArrayList<Event>(
				puntual_events_query.getResultList());

		return result;
	}

	public List<Event> searchByText(String text_search) {
		List<Event> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT e FROM Event e " + 
						"WHERE upper(e.title) LIKE upper(:search) OR upper(e.description) LIKE upper(:search)")
		.setParameter("search", "%"+text_search+"%");

		result = new ArrayList<Event>(
				puntual_events_query.getResultList());

		return result;
	}

	public List<Event> getPromotedEvents(Date date) {
		List<EventPromotion> event_promotions = null;

		// System.out.println(domingoSemana);
		Query event_promotions_query = entityManager
				.createQuery("SELECT ep FROM EventPromotion ep ");

		event_promotions = new ArrayList<EventPromotion>(
				event_promotions_query.getResultList());

		List<Event> result = new ArrayList<Event>(event_promotions.size());
		for(EventPromotion ep : event_promotions){
			result.add(ep.getPromotedEvent());
		}
		return result;
	}
}
