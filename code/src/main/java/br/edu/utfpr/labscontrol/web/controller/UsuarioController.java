package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.CfgEnvioEmail;
import br.edu.utfpr.labscontrol.model.entity.PasswordResetToken;
import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.enumeration.RolesEnum;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CfgEnvioEmailService;
import br.edu.utfpr.labscontrol.model.service.PasswordResetTokenService;
import br.edu.utfpr.labscontrol.model.service.PermissaoService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.EnumUtil;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    @Autowired
    private CfgEnvioEmailService cfgEnvioEmailService;

    private Usuario usuarioLogado;
    private Boolean isAdmin;
    private SelectItem[] rolesItem;
    private RolesEnum rolesEnum;
    private String password;
    private String email;
    private String idUserToChangePass;
    private String tokenToChangePass;

    @Override
    protected ICrudService<Usuario, Integer> getService() {
        return this.usuarioService;
    }

    @Override
    protected void inicializar() {
        checkUsuarioLogado();
        HttpServletRequest request = JsfUtil.getRequest();
        idUserToChangePass = request.getParameter("id");
        tokenToChangePass = request.getParameter("token");
        rolesItem = EnumUtil.populaSelect(RolesEnum.values());
        if (this.entity instanceof Usuario) {
             setPermissao();
        }
    }

    @Override
    public void find() {
        if (!somenteAdm()) {
            lsEntity.clear();
            lsEntity.add(usuarioService.findByUsername(usuarioLogado.getUsername()));
        } else {
            super.find();
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
                } else if (p.getId() == (RolesEnum.NONE.ordinal() + 1)) {
                    rolesEnum = RolesEnum.NONE;
                }
            }
        }
    }

    private void checkUsuarioLogado() {
        if (this.usuarioLogado == null) {
            this.usuarioLogado = JsfUtil.getUsuarioLogado();
            if (this.usuarioLogado.getId() != null) {
                for (Permissao p : this.usuarioLogado.getPermissoes()) {
                    JsfUtil.setAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO, p);
                }
            }
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
        } else if (!somenteAdm()) {
            throw new Exception("Você não possui permissão para exclusão!");
        }
    }

    @Override
    protected void preProcessorDeleteFromForm() throws Exception {
        if (somenteAdm()) {
            super.preProcessorDeleteFromForm();
        } else {
            throw new Exception("Você não possui permissão para exclusão!");
        }
    }

    @Override
    protected Usuario preProcessorSave(Usuario entity) {
        try {
            if (isAdmin == null) {
                isAdmin = Boolean.FALSE;
            }
            //Evita que o usuário tenha mais de uma ROLE
            this.entity.setPermissoes(new HashSet<>());
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
            String role = (isAdmin) ? "ROLE_ADMIN" : "ROLE_NONE";
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
        String[] roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_ATENDENTE", "ROLE_NONE"};
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

    public Boolean somenteAdm() {
        return ((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() == RolesEnum.ADM.ordinal() + 1;
    }

    public Boolean somenteAdmEatendente() {
        return ( (((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() != RolesEnum.USER.ordinal() + 1) &&
                 (((Permissao)JsfUtil.getAttributeSession(JsfUtil.PERMISSAO_USUARIO_LOGADO)).getId() != RolesEnum.NONE.ordinal() + 1) );
    }

    public void enviaEmail() {
        Usuario u = usuarioService.findByEmail(this.email);
        if (u == null) {
            addMessage("Não existe usuário cadastrado com esse email!", FacesMessage.SEVERITY_ERROR);
        } else {
            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken();
            passwordResetToken.setUser(u);
            passwordResetToken.setToken(token);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            passwordResetToken.setExpiryDate(c.getTime());
            try {
                passwordResetTokenService.save(passwordResetToken);
            } catch (Exception e) {
                e.printStackTrace();
            }

            HttpServletRequest request = JsfUtil.getRequest();
            String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            try {
                SimpleEmail email = constructResetTokenEmail(appUrl, token, u);
                System.out.println("enviando...");
                email.send();
                System.out.println("Email enviado.");
                addMessage("Email enviado com sucesso! Verifique sua caixa de entrada para proceder a alteração da senha.", FacesMessage.SEVERITY_INFO);
            } catch (EmailException e) {
                addMessage("Problemas ao enviar email. Entre em contato com o administrador!", FacesMessage.SEVERITY_ERROR);
                e.printStackTrace();
            }
        }
    }

    private SimpleEmail constructResetTokenEmail(String contextPath, String token, Usuario user) throws EmailException {
        String url = contextPath + "/mudarSenha.xhtml?id=" + user.getId() + "&token=" + token;
        String message = "Recuperação/Alteração de senha";

        CfgEnvioEmail cfgEnvioEmail = cfgEnvioEmailService.findByAtiva(Boolean.TRUE);

        SimpleEmail email = new SimpleEmail();
        email.setHostName(cfgEnvioEmail.getHostName());
        //Quando a porta utilizada não é a padrão (gmail = 465)
        email.setSmtpPort(cfgEnvioEmail.getPort());
        //Configure o seu email do qual enviará
        email.setFrom(cfgEnvioEmail.getEmail(), "Recuperação/Alteração de senha - LabsControl/UTFPR");
        //Adicione os destinatários
        email.addTo(this.email, this.email);
        //Adicione um assunto
        email.setSubject("Recuperação/Alteração de senha no sistema LabsControl - UTFPR");
        //Adicione a mensagem do email
        email.setMsg(message + "\n\n" + url);
        //Para autenticar no servidor é necessário chamar os dois métodos abaixo
        email.setSSL(true);
        System.out.println("autenticando...");
        email.setAuthenticator(new DefaultAuthenticator(cfgEnvioEmail.getEmail(), cfgEnvioEmail.getSenha()));
        return email;
    }

    public void changePassword() {
        PasswordResetToken byToken = passwordResetTokenService.findByToken(tokenToChangePass);
        Usuario user = byToken.getUser();
        if (byToken == null || user.getId() != Integer.valueOf(idUserToChangePass)) {
            addMessage("Token inválido.", FacesMessage.SEVERITY_ERROR);
        } else {
            Calendar cal = Calendar.getInstance();
            if ((byToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
                addMessage("Token já expirou.", FacesMessage.SEVERITY_ERROR);
            } else {
                user.setPassword(user.getEncodePassword(this.password));
                this.entity = user;
                save();
                addMessage("Senha alterada com sucesso!", FacesMessage.SEVERITY_INFO);
                this.tokenToChangePass = null;
                this.idUserToChangePass = null;
            }
        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}