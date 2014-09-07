package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EmprestimoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class EmprestimoController extends CrudController<Emprestimo, Integer> {

    @Autowired
    private EmprestimoService emprestimoService;

    @Override
    protected ICrudService<Emprestimo, Integer> getService() {
        return this.emprestimoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/emprestimo/form.xhtml?faces-redirect=true";
    }
}
