package org.fewnuts.rutadaki.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.fewnuts.rutadaki.domain.StoredLocation;
import org.fewnuts.rutadaki.persistence.dao.StoredLocationDao;

public class StoredLocationDaoJPA extends
		GenericJPADAO<StoredLocation, Long> implements
		StoredLocationDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public StoredLocationDaoJPA(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<StoredLocation> getAll() {
		List<StoredLocation> result = null;

		// System.out.println(domingoSemana);
		Query query = entityManager.createQuery(
				"SELECT l FROM StoredLocation l");
		result = new ArrayList<StoredLocation>(
				query.getResultList());

		return result;
	}
	
}
