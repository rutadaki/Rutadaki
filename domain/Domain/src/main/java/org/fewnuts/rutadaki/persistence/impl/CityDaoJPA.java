package org.fewnuts.rutadaki.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.fewnuts.rutadaki.domain.City;
import org.fewnuts.rutadaki.persistence.dao.CityDao;

public class CityDaoJPA extends
		GenericJPADAO<City, Long> implements
		CityDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public CityDaoJPA(EntityManager em) {
		super(em);
	}

	public List<City> getAll() {
		List<City> result = null;

		// System.out.println(domingoSemana);
		Query puntual_events_query = entityManager
				.createQuery("SELECT c FROM City c ");

		result = new ArrayList<City>(
				puntual_events_query.getResultList());

		return result;
	}
}
