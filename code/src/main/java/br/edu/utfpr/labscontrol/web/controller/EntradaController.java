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

import javax.faces.application.FacesMessage;
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
        Object o = JsfUtil.getAttributeSession("tipo");
        if (o != null) {
            this.tipo = String.valueOf(o);
        } else if (entity.getMaterialDeConsumo() != null) {
            this.tipo = "M";
        } else if (entity.getEquipamento() != null) {
            this.tipo = "E";
        } else {
            this.tipo = "N";
        }
        o = JsfUtil.getAttributeSession("equipamento");
        if (o != null) {
            this.entity.setEquipamento((Equipamento)o);
            this.entity.setFornecedor(((Equipamento)o).getFornecedor());
        }
        o = JsfUtil.getAttributeSession("materialDeConsumo");
        if (o != null) {
            this.entity.setMaterialDeConsumo((MaterialDeConsumo)o);
            this.entity.setFornecedor(((MaterialDeConsumo)o).getFornecedor());
        }
        JsfUtil.removeAttributeSession("tipo");
        JsfUtil.removeAttributeSession("equipamento");
        JsfUtil.removeAttributeSession("materialDeConsumo");
    }

    @Override
    protected Boolean validacaoSave(Entrada entity) {
        Boolean valido = !(entity.getEquipamento() == null && entity.getMaterialDeConsumo() == null);
        if (!valido) {
            addMessage("Deve ser informado Material de Consumo ou Equipamento!", FacesMessage.SEVERITY_ERROR);
        }
        return valido;
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
