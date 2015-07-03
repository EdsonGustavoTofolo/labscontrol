/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.labscontrol.web.util;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class JsfUtil {
    public static final String PERMISSAO_USUARIO_LOGADO = "permissaoUsuarioLogado";

    public static void clearSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if(session != null){
            session.invalidate();
        }
        facesContext.getExternalContext().getSession(true);
    }
    
    public static HttpSession getSession() {
         FacesContext contexto = FacesContext.getCurrentInstance();
         return (HttpSession) contexto.getExternalContext().getSession(true);
    }
    
    public static void setAttributeSession(final String name, final Object object) {
        HttpSession session = getSession();
        session.setAttribute(name, object);
    }
    
    public static String getRequestParameter(final String parameter) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);
    }
    
    public static Object getAttributeSession(final String name) {
        try {
            HttpSession session = getSession();
            return session.getAttribute(name);
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static void removeAttributeSession(final String name) {
        HttpSession session = getSession();
        session.removeAttribute(name);
    }
    
    public static void addMessage(String message, FacesMessage.Severity Severity) {
        FacesContext.getCurrentInstance().addMessage("controller", new FacesMessage(Severity, message, message));
    }
    
    public static ELContext getElContext() {
        return FacesContext.getCurrentInstance().getELContext();
    }
    
    public static ExpressionFactory getExpressionFactory() {
        return FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
    }
    
    public static String getParameter(String parameter) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);
    }
    
    public static String getRequestURL() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static String getRealPath() {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getServletContext().getRealPath("/");
    }
    
    public static String getContextPath() {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getServletContext().getContextPath();
    }
    
    public static String getURL() {
        final String context = getContextPath();
        
        String url = JsfUtil.getRequestURL();
        return url.substring(0, url.indexOf(context)) + context + "/";
    }
    
    public static void redirect(String page) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            
        }
    }

    public static Object getFlashParameter(String name){
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(name);
    }

    public static void setFlashParameter(String name, Object value){
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(name, value);
    }

    public static Usuario getUsuarioLogado() {
        Usuario usuario = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                try {
                    usuario = (Usuario) authentication.getPrincipal();
                } catch (Exception e) {
                    usuario.setUsername("Desconhecido");
                    usuario.setNome("Desconhecido");
                }
            }
        }
        return usuario;
    }

    /**
     * Retorna o estágio em que o projeto foi definido no web.xml
     * @return ProjectStage.Development ou ProjectStage.Production
     */
    public static ProjectStage getProjectStage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        return application.getProjectStage();
    }

    /**
     * Se o projeto estvier no estágio de Desenvolvimento
     * @return TRUE se o projeto estiver no estágio de Desenvolvimento conforme definido no web.xml
     */
    public static Boolean isDevelopmentProjectStage() {
        return getProjectStage() == ProjectStage.Development;
    }

    /**
     * Retorna os seguintes dados conforme estágio do projeto:
     * Se em Desenvolvimento:      Se em Produção:
     * dbName = labscontrol        edson
     * dbUser = root               edson
     * dbPass = root               edson123
     */
    public static Map<String, String> getDadosDBforBkp() {
        Map<String, String> dados = new HashMap<>();
        if (isDevelopmentProjectStage()) {
            dados.put("dbName", "labscontrol");
            dados.put("dbUser", "root");
            dados.put("dbPass", "root");
        } else {
//            dados.put("dbName", "edsondainf");
            dados.put("dbName", "edson");
            dados.put("dbUser", "edson");
            dados.put("dbPass", "edson123");
        }
        return dados;
    }

    /**
     * Faz o download direto do arquivo.pdf
     */
    public static void printAndDownloadPdf(List list, String reportName, Map parameters, String pdfName) {
//        List<Equipamento> lista = this.equipamentoService.findAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/pages/relatorios/EquipamentosReport.jasper");
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/pages/relatorios/" + reportName);
        try {
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), dataSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, dataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + pdfName + ".pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FacesContext.getCurrentInstance().responseComplete();
        }
    }

    /**
     * Exibe o arquivo no browser
     * @param list - Lista dos registros que serão apresentados no relatório
     * @param reportName - Nome do arquivo .jasper
     * @param parameters - Parâmetros
     * @param pdfName - Nome do arquivo que será gerado pdf
     */
    public static void printAndShowOnBrowser(List list, String reportName, Map parameters, String pdfName) {
//        List<Equipamento> lista = this.equipamentoService.findAll();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

//        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/pages/relatorios/EquipamentosReport.jasper");
        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/pages/relatorios/" + reportName);

        response.setContentType("application/pdf");

        response.setHeader("Content-Disposition", "inline;filename=" + pdfName + ".pdf");

        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();

            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(list);

            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameters, datasource);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            facesContext.responseComplete();
        }
    }

}
