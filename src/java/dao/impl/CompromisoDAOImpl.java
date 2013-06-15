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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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
   
    @Override
    public List<Compromisso> listAllCompromissosUser(Pessoa user) {
        ss = HibernateUtil.getSessionFactory().openSession();
         Criteria c = ss.createCriteria(Compromisso.class);
         Criterion USER =  Restrictions.eq("usuario", user);
         c.add(USER);
         
//         Date data = new Date();            
//         Criterion DIA = Restrictions.sqlRestriction(" dia > " + data);
//         c.add(DIA);
         
         c.addOrder(Order.asc("dia"));
         
         return c.list();
    }
}


