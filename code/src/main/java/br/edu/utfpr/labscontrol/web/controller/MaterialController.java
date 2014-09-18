package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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

    private boolean isEquipamento;
    private Equipamento equipamento;
    private MaterialDeConsumo materialDeConsumo;

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

    public boolean isEquipamento() {
        return isEquipamento;
    }

    public void setIsEquipamento(boolean isEquipamento) {
        this.isEquipamento = isEquipamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
