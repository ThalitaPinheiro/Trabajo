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
    private boolean camposObg = true;
    private static Compromisso comp;

    public CompromisoBean() {

        user = usuario;
        if (usuario == null) {
            RedirecionamentosBean.pageError();
            return;
        }
        camposObg = true;
        agenda = daoCompromiso.listAllCompromissosUser(usuario);
    }

    public static Compromisso getComp() {
        return comp;
    }

    public static void setComp(Compromisso comp) {
        CompromisoBean.comp = comp;
    }

    public boolean isCamposObrigatorios() {
        return camposObg;
    }

    public void setCamposObg(boolean camposObg) {
        this.camposObg = camposObg;
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

//    public String logout() {
//        usuario = null;
//        return "index?faces-redirect=true";
//    }

    public String salvar() {
        //this.removeMascara();
        compromisso.setUsuario(usuario);
        try {
            compromisso = daoCompromiso.save(compromisso);
        } catch (Exception x) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error"));
            Mensagens.avisoErro("Ocorrió un error y no fué possible salvar");
        }
        agenda.clear();
        agenda = daoCompromiso.listAllCompromissosUser(usuario);
        return "agenda?faces-redirect=true";

    }

    public String deletar() {
//        daoCompromiso.delete(compromisso);
//        daoCompromiso = new CompromisoDAOImpl();
//        agenda.clear();
//        agenda = daoCompromiso.listAllCompromissosUser(usuario);

        return "agenda?faces-redirect=true";

    }

    public String nuevoCompromiso() {
        if (usuario == null) {
            Mensagens.avisoErro("No tiene permision para hacer esto.");
            return "/faces/acessDenied?faces-redirect=true";
        } else {
            compromisso = new Compromisso();
            camposObg = true;
            return "/faces/nuevoCompromiso?faces-redirect=true";
        }
    }

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Importância", "La importancia del compromiso es:"
                + ((Integer) rateEvent.getRating()).intValue());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String voltar() {
        if (usuario == null) {
            return "/faces/acessDenied?faces-redirect=true";
        } else {
            return "agenda?faces-redirect=true";
        }
    }

    public void camposObrigatorio(boolean x) {
        camposObg = x;
    }

   public String paginaAlterar(){
     
        return "editarCompromiso?faces-redirect=true";    
    }
    
    public String salvarCambio() {
        try {
            compromisso = daoCompromiso.update(compromisso);
        } catch (Exception x) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error"));
            Mensagens.avisoErro("Ocorrió un error y no fué possible cambiar");
        }
        agenda.clear();
        agenda = daoCompromiso.listAllCompromissosUser(user);
        return "agenda?faces-redirect=true";

    }
}
