package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class MaterialDeConsumoController extends CrudController<MaterialDeConsumo, Integer> {

    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;

    @Override
    protected ICrudService<MaterialDeConsumo, Integer> getService() {
        return this.materialDeConsumoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/materialdeconsumo/materialDeConsumoForm.xhtml?faces-redirect=true";
    }
}
