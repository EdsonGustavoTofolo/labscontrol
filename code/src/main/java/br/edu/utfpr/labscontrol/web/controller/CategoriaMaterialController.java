package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaMaterialService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class CategoriaMaterialController extends CrudController<CategoriaMaterial, Integer> {
    @Autowired
    private CategoriaMaterialService categoriaMaterialService;

    @Override
    protected ICrudService<CategoriaMaterial, Integer> getService() {
        return this.categoriaMaterialService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/categoria/material/categoriaMaterialForm.xhtml?faces-redirect=true";
    }
}
