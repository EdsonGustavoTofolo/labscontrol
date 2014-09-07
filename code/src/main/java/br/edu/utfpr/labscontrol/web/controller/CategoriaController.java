package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Categoria;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class CategoriaController extends CrudController<Categoria, Integer> {

    @Autowired
    private CategoriaService categoriaService;

    @Override
    protected ICrudService<Categoria, Integer> getService() {
        return this.categoriaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/categoria/categoriaForm.xhtml?faces-redirect=true";
    }
}
