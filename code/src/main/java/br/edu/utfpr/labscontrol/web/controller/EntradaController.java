package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Entrada;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EntradaService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by EdsonGustavo on 08/03/2015.
 */
@Controller
@Scope("view")
public class EntradaController extends CrudController<Entrada, Integer> {
    @Autowired
    private EntradaService entradaService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;
    @Autowired
    private FornecedorService fornecedorService;

    private String tipo;

    @Override
    public void init() {
        super.init();
        Object o = JsfUtil.getFlashParameter("tipo");
        if (o != null) {
            this.tipo = String.valueOf(o);
        } else if (entity.getMaterialDeConsumo() != null) {
            this.tipo = "M";
        } else if (entity.getEquipamento() != null) {
            this.tipo = "E";
        } else {
            this.tipo = "N";
        }
    }

    @Override
    protected ICrudService<Entrada, Integer> getService() {
        return entradaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/movimentos/entrada/entradaForm.xhtml?faces-redirect=true";
    }

    public List<Equipamento> completeEquipamento(String nome) {
        return equipamentoService.findByNomeContaining(nome);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    public List<Fornecedor> completeFornecedor(String nomeFantasia) {
        return fornecedorService.findByNomeFantasiaContaining(nomeFantasia);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
