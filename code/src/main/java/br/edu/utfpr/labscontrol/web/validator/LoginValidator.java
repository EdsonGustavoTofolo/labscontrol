package br.edu.utfpr.labscontrol.web.validator;

import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import br.edu.utfpr.labscontrol.model.service.impl.UsuarioServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 * Created by EdsonGustavo on 11/04/2015.
 */
@Component
@Scope("request")
public class LoginValidator implements Validator {
    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object login) throws ValidatorException {
        try {
            validaLogin(String.valueOf(login));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Login já está em uso!", "Login já está sendo usado por outro usuário!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    private void validaLogin(String login) throws Exception {
        if (StringUtils.isNotBlank(login)) {
            if (usuarioService.findByUsername(login) != null) {
                throw new Exception();
            }
        }
    }


}
