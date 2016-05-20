package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.MarcaMaterial;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaMaterialService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class MarcaMaterialController extends CrudController<MarcaMaterial, Integer> {
    @Autowired private MarcaMaterialService marcaMaterialService;
    @Override
    protected ICrudService<MarcaMaterial, Integer> getService() {
        return this.marcaMaterialService;
    }
    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/marca/material/marcaMaterialForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/marca/material/marcaMaterialSearch.xhtml?faces-redirect=true";
    }
}
