/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Compromisso;
import model.Pessoa;

/**
 *
 * @author Thalita
 */
public interface  CompromisoDAO extends GenericDAO<Compromisso, Integer>  {
    public abstract List<Compromisso> listAllCompromissosUser(Pessoa user);
}
