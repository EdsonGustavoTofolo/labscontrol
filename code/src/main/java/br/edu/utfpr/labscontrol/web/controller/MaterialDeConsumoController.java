package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class MaterialDeConsumoController extends CrudController<MaterialDeConsumo, Integer> {
    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;
    @Autowired
    private CategoriaMaterialService categoriaMaterialService;
    @Autowired
    private MarcaMaterialService marcaMaterialService;
    @Autowired
    private ModeloMaterialService modeloMaterialService;
    @Autowired
    private FornecedorService fornecedorService;

    private BigDecimal quantidade;
    private UploadedFile uploadedFile;

    @Override
    public void init() {
        this.quantidade = BigDecimal.ZERO;
        super.init();
    }

    @Override
    protected ICrudService<MaterialDeConsumo, Integer> getService() {
        return this.materialDeConsumoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/materialdeconsumo/materialDeConsumoForm.xhtml?faces-redirect=true";
    }

    @Override
    protected void preProcessorDelete() {
        removeImageFile();
    }

    @Override
    protected MaterialDeConsumo preProcessorSave(MaterialDeConsumo entity) {
        if ( this.entity.getQtdAtual().compareTo(this.entity.getQtdMin()) == -1 ) {
            addMessage("Quantidade Atual está abaixo da Quantidade Mínima!", FacesMessage.SEVERITY_WARN);
        }
        return super.preProcessorSave(entity);
    }

    private void removeImageFile() {
        try {
            String pathFile = getRealPath() + "\\" + this.materialDeConsumoService.findById(getId()).getFoto();
            File imgFile = new File(pathFile);
            if (!imgFile.delete()) {
                Logger.getLogger(Equipamento.class).info("Não foi possível remover a imagem: " + pathFile);
            }
        } catch (Exception e) {
            addMessage("Erro ao excluir imagem! " + e.getMessage(), FacesMessage.SEVERITY_INFO);
            e.printStackTrace();
        }
    }

    public void movimentaSaida() {
        JsfUtil.setAttributeSession("tipo", "M");
        JsfUtil.redirect("/labscontrol/pages/movimentos/saida/saidaForm.xhtml?faces-redirect=true");
    }

    public void movimentaEntrada() {
        JsfUtil.setAttributeSession("tipo", "M");
        JsfUtil.redirect("/labscontrol/pages/movimentos/entrada/entradaForm.xhtml?faces-redirect=true");
    }

    public List<CategoriaMaterial> completeCategoria(String nome) {
        return categoriaMaterialService.findByNomeContaining(nome);
    }

    public List<ModeloMaterial> completeModelo(String nome) {
        return modeloMaterialService.findByNomeContaining(nome);
    }

    public List<MarcaMaterial> completeMarca(String nome) {
        return marcaMaterialService.findByNomeContaining(nome);
    }

    public List<Fornecedor> completeFornecedor(String nomeFantasia) {
        return fornecedorService.findByNomeFantasiaContaining(nomeFantasia);
    }

    public void handleKeyEvent() {
        BigDecimal soma =  entity.getQtdAtual().add(this.quantidade, MathContext.DECIMAL64);
        entity.setQtdAtual(soma);
    }

    public String getRealPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\fotos\\materiaisDeConsumo");
    }

    public String getFullPath() {
        return "../../../fotos/materiaisDeConsumo/" + entity.getFoto();
    }

    public void fileUploadListener(FileUploadEvent event){
        this.uploadedFile = event.getFile();
        try {
            String fileName = event.getFile().getFileName();
            String path = getRealPath();
            File targetFolder = new File(path);
            this.entity.setFoto(fileName);
            if (!targetFolder.exists()) {
                targetFolder.mkdir();
            }
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream outputStream = new FileOutputStream(new File(targetFolder, fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            addMessage("Erro ao fazer upload da imagem! " + e.getMessage(), FacesMessage.SEVERITY_INFO);
            e.printStackTrace();
        }
    }

    public String lancarSaida(MaterialDeConsumo materialDeConsumo) {
        JsfUtil.setAttributeSession("materialDeConsumo", materialDeConsumo);
        JsfUtil.setAttributeSession("tipo", "M");
        return "/pages/movimentos/saida/saidaForm.xhtml?faces-redirect=true";
    }

    public String lancarEntrada(Equipamento equipamento) {
        JsfUtil.setAttributeSession("materialDeConsumo", equipamento);
        JsfUtil.setAttributeSession("tipo", "M");
        return "/pages/movimentos/entrada/entradaForm.xhtml?faces-redirect=true";
    }

    public BigDecimal getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
