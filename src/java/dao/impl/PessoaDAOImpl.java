
package dao.impl;

/**
 *
 * @author Thalita
 */
import dao.PessoaDAO;
import model.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import utils.HibernateUtil;

public class PessoaDAOImpl extends GenericDAOImpl<Pessoa, Integer> implements PessoaDAO {

    Session ss = null;
    Transaction tx = null;
    
    public PessoaDAOImpl() {
    }
    
    public Pessoa getPessoaByEmail(String email)
    {
        ss = HibernateUtil.getSessionFactory().openSession();
        Criteria c = ss.createCriteria(Pessoa.class);
        Criterion EMAIL = Restrictions.eq("email", email);  
        c.add(EMAIL);
        return (Pessoa) c.uniqueResult();           
    }
    
   
}