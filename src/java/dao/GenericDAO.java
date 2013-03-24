
package dao;

import java.io.Serializable;
import java.util.List;
/**
 * Interface da GenericDAO
 * Essa interface faz a comunicação entre a aplicação e a base de dados.
 * @author thalita
 */
public interface GenericDAO<T, ID extends Serializable> {

   
    T getById(ID id);

  
    List<T> listAll();

 
    T save(T entity);

    
    T update(T entity);

    
    void delete(T entity);
}