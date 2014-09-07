package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class MaterialController extends CrudController<Material, Integer> {

    @Override
    protected ICrudService<Material, Integer> getService() {
        return null;
    }

    @Override
    protected String getUrlFormPage() {
        return null;
    }
}
