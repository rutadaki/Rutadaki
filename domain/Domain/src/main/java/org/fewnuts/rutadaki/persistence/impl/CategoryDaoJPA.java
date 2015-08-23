package org.fewnuts.rutadaki.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.persistence.dao.CategoryDao;

public class CategoryDaoJPA extends
		GenericJPADAO<Category, Long> implements
		CategoryDao {

	/**
	 * Default Constructor
	 * 
	 * @param em
	 */
	public CategoryDaoJPA(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAll() {
		List<Category> result = null;

		// System.out.println(domingoSemana);
		Query query = entityManager.createQuery(
				"SELECT c FROM Category c");
		result = new ArrayList<Category>(
				query.getResultList());

		return result;
	}
	
}
