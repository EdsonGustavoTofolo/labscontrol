package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Marca;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class MarcaController extends CrudController<Marca, Integer> {

    @Autowired
    private MarcaService marcaService;

    @Override
    protected ICrudService<Marca, Integer> getService() {
        return this.marcaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/marca/marcaForm.xhtml?faces-redirect=true";
    }
}
