package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Estado;
import br.edu.utfpr.labscontrol.model.entity.Pais;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EstadoService;
import br.edu.utfpr.labscontrol.model.service.PaisService;
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
public class EstadoController extends CrudController<Estado, Integer> {
    @Autowired
    private EstadoService estadoService;
    @Autowired
    private PaisService paisService;

    @Override
    protected ICrudService<Estado, Integer> getService() {
        return estadoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/estado/estadoForm.xhtml?faces-redirect=true";
    }

    @Override
    public String getUrlSearchPage() {
        return "/pages/cadastros/estado/estadoSearch.xhtml?faces-redirect=true";
    }

    public List<Pais> completePais(String nome) {
        return paisService.findByNomeContaining(nome);
    }
}
