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
import org.fewnuts.rutadaki.domain.LongEvent;
import org.fewnuts.rutadaki.domain.utils.DateUtilities;
import org.fewnuts.rutadaki.persistence.dao.LongEventDao;

public class LongEventDaoJPA extends
		GenericJPADAO<LongEvent, Long> implements
		LongEventDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public LongEventDaoJPA(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<LongEvent> getLongEventsByDay(int day, int month, int year) {

		List<LongEvent> result = new LinkedList<LongEvent>();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		Date date = calendar.getTime();

		List<LongEvent> long_events = new LinkedList<LongEvent>();
		
		Query Long_events_query = entityManager.createQuery(
				"SELECT e FROM LongEvent e " + "WHERE e.startDate <= :date"
						+ " AND (e.endDate >= :date OR e.endDate IS NULL)")
				.setParameter("date", date, TemporalType.DATE);
		long_events = new ArrayList<LongEvent>(
				Long_events_query.getResultList());
		
		for(LongEvent e : long_events){
			if(e.getTimeTableDay(date) == null || e.getTimeTableDay(date).size() != 0){
				result.add(e);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<LongEvent> getLongEventsByMonth(int month, int year) {

		Date first_date = DateUtilities.getFirstDayMonth(month, year);
		Date last_date = DateUtilities.getLastDayMonth(month, year);
		
		List<LongEvent> long_events = new LinkedList<LongEvent>();
		
		Query Long_events_query = entityManager
				.createQuery(
						"SELECT e FROM LongEvent e "
								+ "WHERE e.startDate <= :last_date AND e.endDate >= :first_date")
				.setParameter("first_date", first_date, TemporalType.DATE)
				.setParameter("last_date", last_date, TemporalType.DATE);
		long_events = new ArrayList<LongEvent>(
				Long_events_query.getResultList());

		return long_events;
	}

	public List<LongEvent> getOnComingEventsByCategory(Category category) {
		List<LongEvent> result = new LinkedList<LongEvent>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date date = calendar.getTime();

		List<LongEvent> long_events = new LinkedList<LongEvent>();
		
		Query Long_events_query = entityManager.createQuery(
				"SELECT e FROM LongEvent e " + "WHERE (e.endDate >= :date OR e.endDate IS NULL) AND :category MEMBER OF e.categories")
				.setParameter("date", date, TemporalType.DATE)
				.setParameter("category", category);
		long_events = new ArrayList<LongEvent>(
				Long_events_query.getResultList());
		
		for(LongEvent e : long_events){
			if(e.getTimeTableDay(date) == null || e.getTimeTableDay(date).size() != 0){
				result.add(e);
			}
		}

		return result;
	}

	public List<LongEvent> getAll() {
		List<LongEvent> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT e FROM LongEvent e ");

		result = new ArrayList<LongEvent>(
				puntual_events_query.getResultList());

		return result;
	}

	public List<LongEvent> searchByText(String text_search) {
		List<LongEvent> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT e FROM LongEvent e " + 
						"WHERE upper(e.title) LIKE upper(:search) OR upper(e.description) LIKE upper(:search)")
		.setParameter("search", "%"+text_search+"%");

		result = new ArrayList<LongEvent>(
				puntual_events_query.getResultList());

		return result;
	}
}
