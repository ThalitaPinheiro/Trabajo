/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Pessoa;

/**
 *
 * @author Thalita
 */

public interface PessoaDAO extends GenericDAO<Pessoa, Integer> {
    public abstract Pessoa getPessoaByEmail(String email);
    public abstract void mataSession();
}
