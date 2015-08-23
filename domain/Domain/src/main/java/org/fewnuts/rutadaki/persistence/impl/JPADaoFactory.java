package org.fewnuts.rutadaki.persistence.impl;

import javax.persistence.EntityManager;

import org.fewnuts.rutadaki.persistence.dao.CategoryDao;
import org.fewnuts.rutadaki.persistence.dao.CityDao;
import org.fewnuts.rutadaki.persistence.dao.DaoFactory;
import org.fewnuts.rutadaki.persistence.dao.EventDao;
import org.fewnuts.rutadaki.persistence.dao.EventPromotionDao;
import org.fewnuts.rutadaki.persistence.dao.LongEventDao;
import org.fewnuts.rutadaki.persistence.dao.PuntualEventDao;
import org.fewnuts.rutadaki.persistence.dao.StoredLocationDao;

/**
 * JPA implementation of {@link DaoFactory}
 * 
 * @author Fernando
 *
 */
public class JPADaoFactory implements DaoFactory {

	EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();
	
	public CategoryDao createCategoryDao() {
		return new CategoryDaoJPA(entityManager);
	}

	public PuntualEventDao createPuntualEventDao() {
		return new PuntualEventDaoJPA(entityManager);
	}

	public LongEventDao createLongEventDao() {
		return new LongEventDaoJPA(entityManager);
	}

	public StoredLocationDao createStoredLocationDao() {
		return new StoredLocationDaoJPA(entityManager);
	}

	public EventDao createEventDao() {
		return new EventDaoJPA(entityManager);
	}

	public CityDao createCityDao() {
		return new CityDaoJPA(entityManager);
	}
	
	public EventPromotionDao createEventPromotionDao() {
		return new EventPromotionDaoJPA(entityManager);
	}
	
	public void beginConectionScope() {
		entityManager = EntityManagerProvider.getInstance().getEntityManager();
	}

	public void endConectionScope() {
		entityManager.close();
	}

	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	

}
