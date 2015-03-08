package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaEquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Controller
@Scope("view")
public class CategoriaEquipamentoController extends CrudController<CategoriaEquipamento, Integer> {
    @Autowired
    private CategoriaEquipamentoService categoriaEquipamentoService;

    @Override
    protected ICrudService<CategoriaEquipamento, Integer> getService() {
        return this.categoriaEquipamentoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/categoria/equipamento/categoriaEquipamentoForm.xhtml?faces-redirect=true";
    }
}
