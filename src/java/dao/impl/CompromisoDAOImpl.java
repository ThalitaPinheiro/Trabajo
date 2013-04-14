/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CompromisoDAO;
import java.util.List;
import model.Compromisso;
import model.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

/**
 *
 * @author Thalita
 */
public class CompromisoDAOImpl extends GenericDAOImpl<Compromisso, Integer> implements CompromisoDAO {

    Session ss = null;
    Transaction tx = null;

    public CompromisoDAOImpl() {
    }
   
    public List<Compromisso> listAllCompromissosUser(Pessoa user) {
        ss = HibernateUtil.getSessionFactory().openSession();
        return ss.createCriteria(Compromisso.class).add(Restrictions.eq("usuario", user)).list();
    }
}
