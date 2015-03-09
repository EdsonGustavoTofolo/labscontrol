package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Controller
@Scope("view")
public class EquipamentoController extends CrudController<Equipamento, Integer> {

    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private HistoricoDeManutencaoService historicoDeManutencaoService;
    @Autowired
    private CategoriaEquipamentoService categoriaEquipamentoService;
    @Autowired
    private ModeloEquipamentoService modeloEquipamentoService;
    @Autowired
    private MarcaEquipamentoService marcaEquipamentoService;
    @Autowired
    private FornecedorService fornecedorService;

    private HistoricoDeManutencao historicoDeManutencao;
    private UploadedFile uploadedFile;

    @Override
    protected ICrudService<Equipamento, Integer> getService() {
        return this.equipamentoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/equipamento/equipamentoForm.xhtml?faces-redirect=true";
    }

    @Override
    protected void preProcessorDelete() {
        removeImageFile();
    }

    public List<CategoriaEquipamento> completeCategoria(String nome) {
        return categoriaEquipamentoService.findByNomeContaining(nome);
    }

    public List<ModeloEquipamento> completeModelo(String nome) {
        return modeloEquipamentoService.findByNomeContaining(nome);
    }

    public List<MarcaEquipamento> completeMarca(String nome) {
        return marcaEquipamentoService.findByNomeContaining(nome);
    }

    public List<Fornecedor> completeFornecedor(String nomeFantasia) {
        return fornecedorService.findByNomeFantasiaContaining(nomeFantasia);
    }

    public void onEdit(RowEditEvent event) {
        HistoricoDeManutencao historicoDeManutencao = (HistoricoDeManutencao) event.getObject();
        saveHistorico(historicoDeManutencao);
    }

    private void saveHistorico(HistoricoDeManutencao historicoDeManutencao) {
        try {
            this.historicoDeManutencaoService.save(historicoDeManutencao);
            addMessage("Histórico salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void saveHistoricoAndUpdateList() {
        saveHistorico(this.historicoDeManutencao);
        entity.setHistoricoDeManutencoes(historicoDeManutencaoService.findByEquipamento(entity));
    }

    public void deleteHistorico() {
        try {
            entity.getHistoricoDeManutencoes().remove(this.historicoDeManutencao);
            historicoDeManutencaoService.deleteById(this.historicoDeManutencao.getId());
            entity.getHistoricoDeManutencoes();
            addMessage("Histórico de manutenção removido com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void novoHistorico() {
        this.historicoDeManutencao = new HistoricoDeManutencao();
        this.historicoDeManutencao.setEquipamento(entity);
    }

    private void removeImageFile() {
        try {
            String pathFile = getRealPath() + "\\" + this.equipamentoService.findById(getId()).getFoto();
            File imgFile = new File(pathFile);
            if (!imgFile.delete()) {
                Logger.getLogger(Equipamento.class).info("Não foi possível remover a imagem: " + pathFile);
            }
        } catch (Exception e) {
            addMessage("Erro ao excluir imagem! " + e.getMessage(), FacesMessage.SEVERITY_INFO);
            e.printStackTrace();
        }
    }

    public String getRealPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\fotos\\equipamentos");
    }

    public String getFullPath() {
        return "../../../fotos/equipamentos/" + entity.getFoto();
    }

    public void fileUploadListener(FileUploadEvent event){
        this.uploadedFile = event.getFile();
        try {
            String fileName = event.getFile().getFileName();
            String path = getRealPath();
            File targetFolder = new File(path);
            if (!targetFolder.exists()) {
                targetFolder.mkdir();
            }
            this.entity.setFoto(fileName);
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

    public void setEntityEmbedded(HistoricoDeManutencao historicoDeManutencao) {
        this.historicoDeManutencao = historicoDeManutencao;
    }

    public HistoricoDeManutencao getEntityEmbedded() {
        return this.historicoDeManutencao;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
