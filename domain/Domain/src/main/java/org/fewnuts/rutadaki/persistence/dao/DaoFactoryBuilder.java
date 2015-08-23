package org.fewnuts.rutadaki.persistence.dao;

import org.fewnuts.rutadaki.persistence.impl.JPADaoFactory;

/**
 * Creates DaoFactories
 * 
 * @author Fernando
 *
 */
public class DaoFactoryBuilder {
	
	/**
	 * Creates a {@link DaoFactory}
	 * 
	 * @return
	 */
	public static DaoFactory createDaoFactory(){
		return new JPADaoFactory();
	}
}
