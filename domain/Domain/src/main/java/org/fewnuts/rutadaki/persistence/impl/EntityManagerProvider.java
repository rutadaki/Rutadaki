/**
 * 
 */
package org.fewnuts.rutadaki.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Provides JPA EntityManagers to application in a simple, easy way.
 * 
 * @author Fernando Llaca
 * @version %I%, %G%
 *
 */
public class EntityManagerProvider {
	
	/**
	 * The persistence unit of the application
	 */
	private static final String PERSISTENCE_UNIT = "test_Rutadaki";
	
	/**
	 * The entity Manager factory
	 */
	private EntityManagerFactory emf;
	
	private static EntityManagerProvider singleton = new EntityManagerProvider();
	
	/**
	 * Constructor for Singleton
	 */
	private EntityManagerProvider() {
		emf = javax.persistence.Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
	/**
	 * Singleton instance for EntityManagerProvider
	 * 
	 * @return A singleton EntityManagerProvider instance
	 */
	public static EntityManagerProvider getInstance() {
		return singleton;
	}
	
	/**
	 * Creates a JPA EntityManager
	 * 
	 * @return an EntityManager
	 */
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager();
		 
		return em;	
	}
	
	/**
	 * Closes the Entity Manager Factory
	 */
	public void close() {
		emf.close();
	}

}
