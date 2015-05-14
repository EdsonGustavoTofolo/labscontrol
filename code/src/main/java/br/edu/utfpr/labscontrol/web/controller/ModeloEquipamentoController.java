package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.ModeloEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloEquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Controller
@Scope("view")
public class ModeloEquipamentoController extends CrudController<ModeloEquipamento, Integer> {
    @Autowired private ModeloEquipamentoService modeloEquipamentoService;
    @Override
    protected ICrudService<ModeloEquipamento, Integer> getService() {
        return this.modeloEquipamentoService;
    }
    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/modelo/equipamento/modeloEquipamentoForm.xhtml?faces-redirect=true";
    }
}
