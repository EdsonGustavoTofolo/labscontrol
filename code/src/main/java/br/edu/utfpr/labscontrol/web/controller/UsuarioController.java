package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class UsuarioController extends CrudController<Usuario, Integer> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected ICrudService<Usuario, Integer> getService() {
        return this.usuarioService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/usuario/form.xhtml?faces-redirect=true";
    }
}
