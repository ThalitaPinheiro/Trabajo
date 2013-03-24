/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CompromisoDAO;
import model.Compromisso;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Thalita
 */
public class CompromisoDAOImpl extends GenericDAOImpl<Compromisso, Integer> implements CompromisoDAO {
     Session ss = null;
    Transaction tx = null;

    public CompromisoDAOImpl() {
    }
    
}
