package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.MarcaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaEquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Controller
@Scope("view")
public class MarcaEquipamentoController extends CrudController<MarcaEquipamento, Integer> {
    @Autowired
    private MarcaEquipamentoService marcaEquipamentoService;

    @Override
    protected ICrudService<MarcaEquipamento, Integer> getService() {
        return this.marcaEquipamentoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/marca/equipamento/marcaEquipamentoForm.xhtml?faces-redirect=true";
    }
}
