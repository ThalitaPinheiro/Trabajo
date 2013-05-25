package controller;

import com.sun.xml.rpc.client.http.CookieJar;
import dao.impl.PessoaDAOImpl;
import java.util.Map;
import javax.faces.application.FacesMessage;
import model.Pessoa;
import utils.Mensagens;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seguridad.Seguridad;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {

    private Pessoa user;
    private PessoaDAOImpl dao;
    private boolean camposNecessarios = true;

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

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();

        Pessoa p = dao.getPessoaByEmail(user.getEmail());
        if (p != null) {
            String hash = Seguridad.criptografar(user.getEmail(), user.getSenha());
            if (p.getSenhaBanco().equals(hash)) {
                System.out.print("AAAAAAAAAAAAAAAEEEEEEEEEEEEEOOOOOOOOOOOOOWWWWWWWWWWWW");
                CompromisoBean.setUsuario(p);
                RedirecionamentosBean.setPaginaCadastro(false);
                
                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
                Cookie cookie = new Cookie("nombre_user", user.getEmail());
                cookie.setMaxAge(9999);
                response.addCookie(cookie);
                
                return "agenda?faces-redirect=true";

            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Contraseña invalida"));
                Mensagens.avisoErro("Contrasenha incorrecta)");
                return null; //login falhou 
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nombre invalido"));
            Mensagens.avisoErro("Nombre de suário incorrecto");
            return null; //login falhou 
        }

    }

    public String logout() {
        dao.mataSession();
        CompromisoBean.usuario = null;
        return "index?faces-redirect=true";
    }

    public void camposObrigatorio(boolean bool) {
        camposNecessarios = bool;
    }

    public void procuraCokie() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> cookies = context.getExternalContext().getRequestCookieMap();
        Cookie cookie = (Cookie) cookies.get("nombre_user");
        if (cookie!=null){
            user.setEmail(cookie.getValue());
        }
    }
}
