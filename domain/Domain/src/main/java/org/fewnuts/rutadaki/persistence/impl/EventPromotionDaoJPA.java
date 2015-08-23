package org.fewnuts.rutadaki.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.fewnuts.rutadaki.domain.Event;
import org.fewnuts.rutadaki.domain.EventPromotion;
import org.fewnuts.rutadaki.persistence.dao.EventPromotionDao;

public class EventPromotionDaoJPA extends
		GenericJPADAO<EventPromotion, Long> implements
		EventPromotionDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public EventPromotionDaoJPA(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<EventPromotion> getAll() {
		List<EventPromotion> result = null;

		// System.out.println(domingoSemana);
		Query query = entityManager.createQuery(
				"SELECT ep FROM EventPromotion ep");
		result = new ArrayList<EventPromotion>(
				query.getResultList());

		return result;
	}

	public List<EventPromotion> getPromotionsEvent(Event e) {
		List<EventPromotion> result = null;

		// System.out.println(domingoSemana);
		Query query = entityManager.createQuery(
				"SELECT ep FROM EventPromotion ep WHERE ep.promotedEvent = :e").setParameter("e", e);
		result = new ArrayList<EventPromotion>(
				query.getResultList());

		return result;
	}
	
}
