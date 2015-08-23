package org.fewnuts.rutadaki.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.fewnuts.rutadaki.domain.Category;
import org.fewnuts.rutadaki.persistence.dao.CategoryDao;
import org.fewnuts.rutadaki.persistence.dao.DaoFactory;
import org.fewnuts.rutadaki.persistence.dao.DaoFactoryBuilder;
import org.fewnuts.rutadaki.services.CategoryServices;

public class CategoryServicesImpl implements CategoryServices {

	public Set<Category> getAll() {
		DaoFactory daoFactory = DaoFactoryBuilder.createDaoFactory();

		daoFactory.beginConectionScope();
		daoFactory.beginTransaction();

		CategoryDao dao = daoFactory.createCategoryDao();
		Set<Category> result = new HashSet<Category>(dao.getAll());
		
		daoFactory.commitTransaction();
		daoFactory.endConectionScope();
		
		return result;
	}

}
