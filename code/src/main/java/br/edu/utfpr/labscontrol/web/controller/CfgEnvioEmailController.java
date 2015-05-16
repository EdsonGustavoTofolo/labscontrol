package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.CfgEnvioEmail;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CfgEnvioEmailService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;

/**
 * Created by EdsonGustavo on 16/05/2015.
 */
@Controller
@Scope("view")
public class CfgEnvioEmailController extends CrudController<CfgEnvioEmail, Integer> {
    @Autowired
    private CfgEnvioEmailService cfgEnvioEmailService;

    @Override
    protected ICrudService<CfgEnvioEmail, Integer> getService() {
        return this.cfgEnvioEmailService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/cfgenvioemail/cfgEnvioEmailForm.xhtml?faces-redirect=true";
    }

    @Override
    protected Boolean validacaoSave(CfgEnvioEmail entity) {
        CfgEnvioEmail byAtiva = cfgEnvioEmailService.findByAtiva(Boolean.TRUE);
        if (byAtiva != null && entity.getAtiva() && byAtiva.getId() != entity.getId()) {
            addMessage("Já existe uma configuração ativa!", FacesMessage.SEVERITY_ERROR);
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
