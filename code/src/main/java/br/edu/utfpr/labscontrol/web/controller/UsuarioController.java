package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.PermissaoService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import br.edu.utfpr.labscontrol.model.service.impl.PermissaoServiceImpl;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.validator.LoginValidator;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.print.attribute.standard.Severity;
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
    private Boolean isAdmin;

    @Override
    protected ICrudService<Usuario, Integer> getService() {
        return this.usuarioService;
    }

    @Override
    protected void inicializar() {
        if (this.usuarioLogado == null) {
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
    }

    public void criarUsuarioAdm() {
        isAdmin = Boolean.TRUE;
        Usuario adm = new Usuario();
        adm.setNome("Administrador");
        adm.setUsername("admin");
        adm.setPassword(adm.getEncodePassword("admin"));
        adm.setEmail("");
        try {
            adm.addPermissao(getPermissao());
            usuarioService.save(adm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        isAdmin = Boolean.FALSE;
    }

    public boolean isUserAdmExist() {
        return usuarioService.findByUsername("admin") != null;
    }

    @Override
    public void reset() {
        this.isAdmin = Boolean.FALSE;
        super.reset();
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/usuario/usuarioForm.xhtml?faces-redirect=true";
    }

    @Override
    protected Usuario preProcessorSave(Usuario entity) {
        try {
            if (isAdmin == null) {
                isAdmin = Boolean.FALSE;
            }
            this.entity.addPermissao(getPermissao());
            this.entity.setPassword(this.entity.getEncodePassword(this.entity.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.entity;
    }

    private Permissao getPermissao() throws Exception {
        String role = (isAdmin) ? "ROLE_ADMIN" : "ROLE_USER";
        Permissao permissao = permissaoService.findByPermissao(role);
        if (permissao == null) {
            permissao = new Permissao();
            permissao.setPermissao(role);
            permissaoService.save(permissao);
        }
        return permissao;
    }

    public boolean isUsuarioLogadoAdmin() {
        return (usuarioLogado.getId() != null) ? usuarioLogado.getPermissoes().contains(permissaoService.findByPermissao("ROLE_ADMIN")) : false;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}