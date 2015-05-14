package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.AmbienteService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class AmbienteController extends CrudController<Ambiente, Integer> {
    @Autowired private AmbienteService ambienteService;
    @Override
    protected ICrudService<Ambiente, Integer> getService() {
        return this.ambienteService;
    }
    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/ambiente/ambienteForm.xhtml?faces-redirect=true";
    }
}
