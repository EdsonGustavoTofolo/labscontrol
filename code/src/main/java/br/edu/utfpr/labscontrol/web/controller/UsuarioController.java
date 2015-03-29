package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.PermissaoService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import br.edu.utfpr.labscontrol.model.service.impl.PermissaoServiceImpl;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import java.security.Security;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class UsuarioController extends CrudController<Usuario, Integer> {

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @Autowired
    private PermissaoService permissaoService;

    private Usuario usuarioLogado;

    @Override
    protected ICrudService<Usuario, Integer> getService() {
        return this.usuarioService;
    }

    @Override
    protected void inicializar() {
        this.usuarioLogado = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                try {
                    this.usuarioLogado = (Usuario) authentication.getPrincipal();
                } catch (Exception e) {
                    this.usuarioLogado.setUsername("Desconhecido");
                }
            }
        }
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/usuario/usuarioFrom.xhtml?faces-redirect=true";
    }

    @Override
    protected Usuario preProcessorSave(Usuario entity) {
        try {
            this.entity.addPermissao(getPermissao());
            this.entity.setPassword(this.entity.getEncodePassword(this.entity.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.entity;
    }

    private Permissao getPermissao() throws Exception {
        Permissao permissao = permissaoService.findByPermissao("ROLE_USER");
        if (permissao == null) {
            permissao = new Permissao();
            permissao.setPermissao("ROLE_USER");
            permissaoService.save(permissao);
        }
        return permissao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
