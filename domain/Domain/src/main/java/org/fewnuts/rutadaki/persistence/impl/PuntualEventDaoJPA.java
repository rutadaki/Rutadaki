package org.fewnuts.rutadaki.persistence.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.domain.PuntualEvent;
import org.fewnuts.rutadaki.domain.utils.DateUtilities;
import org.fewnuts.rutadaki.persistence.dao.PuntualEventDao;

public class PuntualEventDaoJPA extends
		GenericJPADAO<PuntualEvent, Long> implements
		PuntualEventDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public PuntualEventDaoJPA(EntityManager em) {
		super(em);
		this.entityManager = em;
	}

	@SuppressWarnings("unchecked")
	public List<PuntualEvent> getPuntualEventsByDay(int day, int month, int year) {

		List<PuntualEvent> result = null;

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		Date date = calendar.getTime();

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager.createQuery(
				"SELECT e FROM PuntualEvent e " + "WHERE e.date = :date")
				.setParameter("date", date, TemporalType.DATE);
		result = new ArrayList<PuntualEvent>(
				puntual_events_query.getResultList());

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<PuntualEvent> getPuntualEventsByMonth(int month, int year) {
		List<PuntualEvent> result = null;

		Date first_date = DateUtilities.getFirstDayMonth(month, year);
		Date last_date = DateUtilities.getLastDayMonth(month, year);

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery(
						"SELECT e FROM PuntualEvent e "
								+ "WHERE e.date >= :first_date AND e.date <= :last_date")
				.setParameter("first_date", first_date, TemporalType.DATE)
				.setParameter("last_date", last_date, TemporalType.DATE);
		result = new ArrayList<PuntualEvent>(
				puntual_events_query.getResultList());

		return result;
	}

	public List<PuntualEvent> getOnComingEventsByCategory(Category category) {
		List<PuntualEvent> result = null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date today = calendar.getTime();

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery(
						"SELECT e FROM PuntualEvent e "
								+ "WHERE e.date >= :date AND :category MEMBER OF e.categories")
				.setParameter("date", today, TemporalType.DATE)
				.setParameter("category", category);
		result = new ArrayList<PuntualEvent>(
				puntual_events_query.getResultList());

		return result;
	}

	public List<PuntualEvent> getAll() {
		List<PuntualEvent> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT e FROM PuntualEvent e ");

		result = new ArrayList<PuntualEvent>(
				puntual_events_query.getResultList());

		return result;
	}

	public List<PuntualEvent> searchByText(String text_search) {
		List<PuntualEvent> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT e FROM PuntualEvent e " + 
						"WHERE upper(e.title) LIKE upper(:search) OR upper(e.description) LIKE upper(:search)")
		.setParameter("search", "%"+text_search+"%");

		result = new ArrayList<PuntualEvent>(
				puntual_events_query.getResultList());

		return result;
	}

	// @Override
	// public PuntualEvent update(PuntualEvent t) {
	// for(Category c : t.getCategories()){
	// entityManager.persist(c);
	// }
	//
	// return (PuntualEvent) entityManager.merge(t);
	// }
}
