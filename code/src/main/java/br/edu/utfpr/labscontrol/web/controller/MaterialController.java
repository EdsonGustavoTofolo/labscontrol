package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.primefaces.event.FileUploadEvent;
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
 * Created by EdsonGustavo on 23/08/2014.
 */
@Controller
@Scope("view")
public class MaterialController extends CrudController<Material, Integer> {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ModeloService modeloService;
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;

    private String tipo;
    private UploadedFile uploadedFile;
    private Equipamento equipamento;
    private MaterialDeConsumo materialDeConsumo;

    @Override
    public void init() {
        super.init();
        if (entity.getMaterialDeConsumo() != null) {
            this.tipo = "M";
        } else if (entity.getEquipamento() != null) {
            this.tipo = "E";
        } else {
            this.tipo = "N";
        }
    }

    public List<Categoria> completeCategoria(String nome) {
        return categoriaService.findByNomeContaining(nome);
    }

    public List<Modelo> completeModelo(String nome) {
        return modeloService.findByNomeContaining(nome);
    }

    public List<Marca> completeMarca(String nome) {
        return marcaService.findByNomeContaining(nome);
    }

    public List<Fornecedor> completeFornecedor(String nomeFantasia) {
        return fornecedorService.findByNomeFantasiaContaining(nomeFantasia);
    }

    public List<Equipamento> completeEquipamento(String nome) {
        return equipamentoService.findByNomeContaining(nome);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    @Override
    protected ICrudService<Material, Integer> getService() {
        return this.materialService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/material/materialForm.xhtml?faces-redirect=true";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void fileUploadListener(FileUploadEvent event){
        try {
            String fileName = event.getFile().getFileName();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\fotos\\");
            File targetFolder = new File(path);
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

            this.entity.setFoto(fileName);
        } catch (Exception e) {
            addMessage("Erro ao fazer upload da imagem! " + e.getMessage(), FacesMessage.SEVERITY_INFO);
            e.printStackTrace();
        }
    }
}
