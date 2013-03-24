/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.GenericDAO;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    Class<T> persistentClass = null;
    private Session session = null;
    private Transaction transaction = null;

    public GenericDAOImpl() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void delete(T entity) {
        try {
            this.transaction = this.session.getTransaction();
            this.transaction.begin();
            this.session.delete(entity);
            this.transaction.commit();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public T getById(ID id) {
        return (T) this.session.get(this.persistentClass, id);
    }

    @Override
    public List<T> listAll() {
        List lista = null;
        try {
            lista = session.createCriteria(this.persistentClass).list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public T save(T entity) {
        try {
            this.transaction = this.session.getTransaction();
            this.transaction.begin();
            this.session.save(entity);
            this.transaction.commit();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            ex.printStackTrace();
        }
        return entity;
    }

    @Override
    public T update(T entity) {
        try {
            this.transaction = this.session.getTransaction();
            this.transaction.begin();
            this.session.update(entity);
            this.transaction.commit();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            ex.printStackTrace();
        }
        return entity;
    }
}
