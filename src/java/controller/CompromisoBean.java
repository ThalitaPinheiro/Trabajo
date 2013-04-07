/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.impl.CompromisoDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Compromisso;
import model.Pessoa;
import org.primefaces.event.RateEvent;
import utils.Mensagens;

/**
 *
 * @author Thalita
 */
@ManagedBean(name = "compromisoBean")
@ViewScoped
public class CompromisoBean {

    static Pessoa usuario;
    private Pessoa user = new Pessoa();
    private CompromisoDAOImpl daoCompromiso = new CompromisoDAOImpl();
    private Compromisso compromisso = new Compromisso();
    private List<Compromisso> agenda = new ArrayList<Compromisso>();

    public CompromisoBean() {
       
        user = usuario;
        if(user==null){
         return ;
        }          
        agenda = daoCompromiso.listAllCompromissosUser(usuario);
    }

    public Pessoa getUser() {
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public Compromisso getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Compromisso compromisso) {
        this.compromisso = compromisso;
    }

    public List<Compromisso> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Compromisso> agenda) {
        this.agenda = agenda;
    }

    public CompromisoDAOImpl getDaoCompromiso() {
        return daoCompromiso;
    }

    public void setDaoCompromiso(CompromisoDAOImpl daoCompromiso) {
        this.daoCompromiso = daoCompromiso;
    }

    public static Pessoa getUsuario() {
        return usuario;
    }

    public static void setUsuario(Pessoa usuario) {
        CompromisoBean.usuario = usuario;
    }

    public String salvar() {
        //this.removeMascara();
        compromisso.setUsuario(usuario);
        compromisso = daoCompromiso.save(compromisso);

        if (!compromisso.getTitulo().isEmpty()) {
            // Mensagens.aviso("Usuário incluído com sucesso!");
            // FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);//put("pessoaBean", this);
            agenda = daoCompromiso.listAllCompromissosUser(usuario);
            return "agenda?faces-redirect=true";
        } else {
            // Mensagens.avisoErro("Falha ao cadastrar usuário."); 
            return null;
        }
    }

    public String deletar() {
        daoCompromiso.delete(compromisso);
        daoCompromiso = new CompromisoDAOImpl();
        agenda = daoCompromiso.listAllCompromissosUser(usuario);
        return "agenda?faces-redirect=true";

    }

    public String nuevoCompromiso() {
        if (usuario == null) {
            return "/faces/acessDenied?faces-redirect=true";
        } else {
            compromisso = new Compromisso();
            return "/faces/nuevoCompromiso?faces-redirect=true";
        }
    }

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Importância", "La importancia del compromiso es:"
                + ((Integer) rateEvent.getRating()).intValue());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
