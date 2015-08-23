/**
 * 
 */
package org.fewnuts.rutadaki.persistence.dao;

import java.io.Serializable;

/**
 * Interface for a generic Data Access Object (DAO)
 * 
 * @author Fernando Llaca
 * @version %I%, %G%
 *
 */
public abstract interface GenericDao<T, PK extends Serializable> {
    
	T create(T t);
	
    T read(PK id);
    
    T update(T t);
    
    void delete(T t);
    
}
