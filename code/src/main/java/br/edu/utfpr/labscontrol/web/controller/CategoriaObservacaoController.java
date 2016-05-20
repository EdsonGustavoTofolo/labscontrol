package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.CategoriaObservacao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaObservacaoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Controller
@Scope("view")
public class CategoriaObservacaoController extends CrudController<CategoriaObservacao, Integer> {
    @Autowired
    private CategoriaObservacaoService categoriaObservacaoService;

    @Override
    protected ICrudService<CategoriaObservacao, Integer> getService() {
        return categoriaObservacaoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/observacao/categoria/categoriaObsForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/observacao/categoria/categoriaObsSearch.xhtml?faces-redirect=true";
    }
}
