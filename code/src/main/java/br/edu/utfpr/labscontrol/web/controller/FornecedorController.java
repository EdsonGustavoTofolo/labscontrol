package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class FornecedorController extends CrudController<Fornecedor, Integer> {

    @Autowired
    private FornecedorService fornecedorService;

    @Override
    protected ICrudService<Fornecedor, Integer> getService() {
        return this.fornecedorService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/fornecedor/fornecedorForm.xhtml?faces-redirect=true";
    }
}
