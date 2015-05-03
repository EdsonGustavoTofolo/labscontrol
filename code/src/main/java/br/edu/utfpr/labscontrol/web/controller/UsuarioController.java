package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.enumeration.RolesEnum;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.PermissaoService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.EnumUtil;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.faces.model.SelectItem;
import java.util.List;

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
    private SelectItem[] rolesItem;
    private RolesEnum rolesEnum;

    @Override
    protected ICrudService<Usuario, Integer> getService() {
        return this.usuarioService;
    }

    @Override
    protected void inicializar() {
        rolesItem = EnumUtil.populaSelect(RolesEnum.values());
        if (this.entity instanceof Usuario) {
             setPermissao();
        }
    }

    private void setPermissao() {
        if (this.entity != null && this.entity.getPermissoes() != null) {
            for (Permissao p : this.entity.getPermissoes()) {
                if (p.getId() == (RolesEnum.ADM.ordinal() + 1)) {
                    rolesEnum = RolesEnum.ADM;
                    this.isAdmin = Boolean.TRUE;
                } else if (p.getId() == (RolesEnum.ATENDENTE.ordinal() + 1)) {
                    rolesEnum = RolesEnum.ATENDENTE;
                } else if (p.getId() == (RolesEnum.USER.ordinal() + 1)) {
                    rolesEnum = RolesEnum.USER;
                }
            }
        }
    }

    private void checkUsuarioLogado() {
        if (this.usuarioLogado == null) {
            this.usuarioLogado = JsfUtil.getUsuarioLogado();
        }
    }

    public void criarUsuarioAdm() {
        try {
            criarPermissoes();
            isAdmin = Boolean.TRUE;
            Usuario adm = new Usuario();
            adm.setNome("Administrador");
            adm.setUsername("admin");
            adm.setPassword(adm.getEncodePassword("admin"));
            adm.setEmail("");
            adm.addPermissao(getPermissao());
            usuarioService.save(adm);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isAdmin = Boolean.FALSE;
        }
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
    protected void preProcessorDelete() throws Exception {
        Usuario u = usuarioService.findById(getId());
        if (u.getPermissoes().contains(permissaoService.findByPermissao("ROLE_ADMIN"))) {
            throw new Exception("Usuário não pode ser excluído!");
        }
    }

    @Override
    protected Usuario preProcessorSave(Usuario entity) {
        try {
            if (isAdmin == null) {
                isAdmin = Boolean.FALSE;
            }
            this.entity.addPermissao(getPermissao());
            if (this.entity.getId() == null) {
                this.entity.setPassword(this.entity.getEncodePassword(this.entity.getPassword()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.entity;
    }

    private Permissao getPermissao() throws Exception {
        Permissao permissao = null;
        if (rolesEnum == null) {
            String role = (isAdmin) ? "ROLE_ADMIN" : "ROLE_USER";
            permissao = permissaoService.findByPermissao(role);
            if (permissao == null) {
                permissao = new Permissao();
                permissao.setPermissao(role);
                permissaoService.save(permissao);
            }
        } else {
            permissao = permissaoService.findById(rolesEnum.ordinal() + 1);
        }
        return permissao;
    }

    private void criarPermissoes() throws Exception {
        String[] roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_ATENDENTE"};
        for(String r : roles) {
            Permissao permissao = new Permissao();
            permissao.setPermissao(r);
            permissaoService.save(permissao);
        }
    }

    public String getUsuarioLogadoNome() {
        checkUsuarioLogado();
        return this.usuarioLogado.getNome();
    }

    public boolean isUsuarioLogadoAdmin() {
        checkUsuarioLogado();
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

    public SelectItem[] getRolesItem() {
        return rolesItem;
    }

    public RolesEnum getRolesEnum() {
        return rolesEnum;
    }

    public void setRolesEnum(RolesEnum rolesEnum) {
        this.rolesEnum = rolesEnum;
    }
}