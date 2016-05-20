package br.edu.utfpr.labscontrol.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by EdsonGTofolo on 12/05/2016.
 */

@FacesValidator(value = "dataEmprestimoValidator")
public class DataEmprestimoValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        try {
            Date data = (Date) value;
            if (data.after(Calendar.getInstance().getTime())) {
                throw new Exception();
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Data inválida! Permitido somente data igual ou anterior a atual!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
