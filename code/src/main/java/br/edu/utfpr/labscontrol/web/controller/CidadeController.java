package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Cidade;
import br.edu.utfpr.labscontrol.model.entity.Estado;
import br.edu.utfpr.labscontrol.model.entity.Pais;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CidadeService;
import br.edu.utfpr.labscontrol.model.service.EstadoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Controller
@Scope("view")
public class CidadeController extends CrudController<Cidade, Integer> {
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private EstadoService estadoService;

    @Override
    protected ICrudService<Cidade, Integer> getService() {
        return cidadeService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/cidade/cidadeForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/cidade/cidadeSearch.xhtml?faces-redirect=true";
    }

    public List<Estado> completeEstado(String nome) {
        return estadoService.findByNomeContaining(nome);
    }
}
