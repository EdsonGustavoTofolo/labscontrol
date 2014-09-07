package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Modelo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class ModeloController extends CrudController<Modelo, Integer> {

    @Autowired
    private ModeloService modeloService;

    @Override
    protected ICrudService<Modelo, Integer> getService() {
        return this.modeloService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/modelo/modeloForm.xhtml?faces-redirect=true";
    }
}
