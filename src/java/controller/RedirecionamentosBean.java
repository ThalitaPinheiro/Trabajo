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
import javax.faces.bean.SessionScoped;


@ManagedBean (name ="redirecionamentosBean")
@SessionScoped
public class RedirecionamentosBean {
    private static boolean paginaCadastro;
    
    public RedirecionamentosBean() {
    }

    public static boolean isPaginaCadastro() {
        System.out.print(paginaCadastro);
        return paginaCadastro;
    }

    public static void setPaginaCadastro(boolean x) {
        RedirecionamentosBean.paginaCadastro = x;
    }
      
    public String cadastroPessoa()
    {
        paginaCadastro=true;
        isPaginaCadastro();
        return "cadastro.xhtml?faces-redirect=true";
    }
    
    public String pageError(){
        if (CompromisoBean.usuario==null){
            return "accessDenied?faces-redirect=true";
        }else {
            return "/Trabajo/faces/agenda.xhtml";
        }     
    }
    
    public String voltarPaginaLogin()
    {
        paginaCadastro=false;
        return "index?faces-redirect=true";
    }
    
    public String cliqueiNoBanner(){
        if(CompromisoBean.usuario==null){
        return "index?faces-redirect=true";
        }else{
        return "agenda?faces-redirect=true";
        }     
    }
}
