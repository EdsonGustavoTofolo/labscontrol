package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.ModeloMaterial;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloMaterialService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class ModeloMaterialController extends CrudController<ModeloMaterial, Integer> {

    @Autowired
    private ModeloMaterialService modeloMaterialService;

    @Override
    protected ICrudService<ModeloMaterial, Integer> getService() {
        return this.modeloMaterialService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/modelo/material/modeloMaterialForm.xhtml?faces-redirect=true";
    }
}
