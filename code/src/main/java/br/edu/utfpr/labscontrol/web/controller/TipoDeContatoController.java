package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.TipoDeContatoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class TipoDeContatoController extends CrudController<TipoDeContato, Integer> {

    @Autowired
    private TipoDeContatoService tipoDeContatoService;

    @Override
    protected ICrudService<TipoDeContato, Integer> getService() {
        return this.tipoDeContatoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/tipodecontato/form.xhtml?faces-redirect=true";
    }
}
