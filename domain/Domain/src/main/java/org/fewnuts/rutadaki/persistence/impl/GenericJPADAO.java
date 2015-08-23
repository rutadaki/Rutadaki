/**
 * 
 */
package org.fewnuts.rutadaki.persistence.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fewnuts.rutadaki.persistence.dao.GenericDao;

/**
 * JPA Generic DAO implementation
 * 
 * @author Fernando Llaca
 * @version %I%, %G%
 * 
 */
public abstract class GenericJPADAO<T, PK extends Serializable> implements
		GenericDao<T, PK> {

	/**
	 * Entity Manager used in transactions with the DB
	 */
	@PersistenceContext // TODO esto que es (persistence context)??
	protected EntityManager entityManager;

	/**
	 * Clase del objeto que este dao gestiona
	 */
	protected Class<T> entityClass;

	/**
	 * Nombre de la entidad del tipo de datos que gestiona este dato
	 */
	//protected String entityName;

	@SuppressWarnings("unchecked")
	public GenericJPADAO() {
		
		// Inicializamos 'entityClass'
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
	             .getGenericSuperclass();
	        this.entityClass = (Class<T>) genericSuperclass
	             .getActualTypeArguments()[0];
//				// Inicializamos 'entityName'
//				entityName = entityClass.getSimpleName();
	}
	
	/**
	 * Crea un Dao asignandole un entity manager
	 * 
	 * @param em
	 */
	protected GenericJPADAO(EntityManager em)
	{
		this();
		this.entityManager = em;
	}

	public T create(T t) {
		this.entityManager.persist(t);
		this.entityManager.flush();
		return t;
	}

	@SuppressWarnings("unchecked")
	public T read(PK id) {
		return (T) this.entityManager.find(entityClass, id);
	}

	public T update(T t) {
		T result = this.entityManager.merge(t);
		
		//this.entityManager.persist(t);
		return result;
		//return t;
	}

	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}
}
