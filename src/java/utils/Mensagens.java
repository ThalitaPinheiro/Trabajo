package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Thalita
 */
public class Mensagens {

    private static FacesContext context = FacesContext.getCurrentInstance();

    public static void aviso(String mensagem) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso:", mensagem));
    }

    public static void avisoErro(String mensagem) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", mensagem));
    }
}
