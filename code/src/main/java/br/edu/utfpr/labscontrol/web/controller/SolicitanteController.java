package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Solicitante;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.SolicitanteService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by EdsonGustavo on 13/06/2015.
 */
@Controller
@Scope("view")
public class SolicitanteController extends CrudController<Solicitante, Integer> {
    @Autowired
    private SolicitanteService solicitanteService;

    @Override
    protected ICrudService<Solicitante, Integer> getService() {
        return solicitanteService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/solicitante/solicitanteForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/solicitante/solicitanteSearch.xhtml?faces-redirect=true";
    }
}
