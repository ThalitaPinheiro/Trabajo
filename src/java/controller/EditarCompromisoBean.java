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
@ManagedBean(name = "editarCompromisoBean")
@ViewScoped
public class EditarCompromisoBean {
    private Pessoa user = new Pessoa();
    private CompromisoDAOImpl daoCompromiso = new CompromisoDAOImpl();
    private static Compromisso compromisso = new Compromisso();
    private List<Compromisso> agenda = new ArrayList<Compromisso>();
    private boolean camposObg = true;

    public EditarCompromisoBean() {
        user=CompromisoBean.usuario;
    }

    public static Compromisso getCompromisso() {
        return compromisso;
    }

    public static void setCompromisso(Compromisso compromisso) {
        EditarCompromisoBean.compromisso = compromisso;
    }

    public Pessoa getUser() {
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public CompromisoDAOImpl getDaoCompromiso() {
        return daoCompromiso;
    }

    public void setDaoCompromiso(CompromisoDAOImpl daoCompromiso) {
        this.daoCompromiso = daoCompromiso;
    }

    public List<Compromisso> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Compromisso> agenda) {
        this.agenda = agenda;
    }

    public boolean isCamposObg() {
        return camposObg;
    }

    public void setCamposObg(boolean camposObg) {
        this.camposObg = camposObg;
    }



      public String paginaAlterar(Compromisso x){
        compromisso=x;        
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
