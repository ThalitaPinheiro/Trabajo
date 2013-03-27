
package controller;

import dao.impl.PessoaDAOImpl;
import javax.faces.application.FacesMessage;
import model.Pessoa;
import utils.Mensagens;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import seguridad.Seguridad;


@ManagedBean (name="loginBean")
@ViewScoped
public class LoginBean {
    private Pessoa user;
    private PessoaDAOImpl dao;
    private boolean camposNecessarios=true;

    public LoginBean() {
        user = new Pessoa();
        dao = new PessoaDAOImpl();
    }

    public Pessoa getUser() {
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public PessoaDAOImpl getDao() {
        return dao;
    }

    public void setDao(PessoaDAOImpl dao) {
        this.dao = dao;
    }

    public boolean isCamposNecessarios() {
        return camposNecessarios;
    }

    public void setCamposNecessarios(boolean camposNecessarios) {
        this.camposNecessarios = camposNecessarios;
    }

      
    
    public String login()
    {
          FacesContext context = FacesContext.getCurrentInstance();
 
        
        String hash = Seguridad.criptografar(user.getEmail(), user.getSenha());
        Pessoa p = dao.getPessoaByEmail(user.getEmail());
        
        if(!p.getSenhaBanco().equals(hash)){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nombre o contraseña invalidos"));
            Mensagens.avisoErro("Usuário ou senha incorreto(s)");
            return null; //login falhou 
        } else
        {      
            System.out.print("AAAAAAAAAAAAAAAEEEEEEEEEEEEEOOOOOOOOOOOOOWWWWWWWWWWWW");
            CompromisoBean.setUsuario(p);
            return "agenda?faces-redirect=true";
        }
    }
    
    public void camposObrigatorio(boolean bool){
        camposNecessarios=bool;    
    }
    
}
