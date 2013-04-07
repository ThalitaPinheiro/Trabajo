/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Thalita
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author arthur
 */

@ManagedBean (name ="redirecionamentosBean")
@ViewScoped
public class RedirecionamentosBean {
    
    public RedirecionamentosBean() {
    }
    
    public String cadastroPessoa()
    {
        return "cadastro.xhtml?faces-redirect=true";
    }
    
//    public String logout()
//    {
//        CompromisoBean.usuario=null;
//        return "index?faces-redirect=true";
//    }
    
//    public String nuevoCompromiso()
//    {
//        return "/faces/nuevoCompromiso?faces-redirect=true";
//    }
}
