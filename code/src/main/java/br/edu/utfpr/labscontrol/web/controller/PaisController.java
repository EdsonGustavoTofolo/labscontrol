package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Pais;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.PaisService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Controller
@Scope("view")
public class PaisController extends CrudController<Pais, Integer> {
    @Autowired
    private PaisService paisService;

    @Override
    protected ICrudService<Pais, Integer> getService() {
        return paisService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/pais/paisForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/pais/paisSearch.xhtml?faces-redirect=true";
    }
}
