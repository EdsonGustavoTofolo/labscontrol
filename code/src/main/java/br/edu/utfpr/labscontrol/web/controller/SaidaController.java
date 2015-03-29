package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.entity.Saida;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.model.service.SaidaService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EdsonGustavo on 01/03/2015.
 */
@Controller
@Scope("view")
public class SaidaController extends CrudController<Saida, Integer> {
    @Autowired
    private SaidaService saidaService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;

    private String tipo;

    @Override
    protected void inicializar() {
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
        }
        o = JsfUtil.getAttributeSession("materialDeConsumo");
        if (o != null) {
            this.entity.setMaterialDeConsumo((MaterialDeConsumo)o);
        }
        JsfUtil.removeAttributeSession("tipo");
        JsfUtil.removeAttributeSession("equipamento");
        JsfUtil.removeAttributeSession("materialDeConsumo");
    }

    @Override
    protected Boolean validacaoSave(Saida entity) {
        Boolean valido = !(entity.getEquipamento() == null && entity.getMaterialDeConsumo() == null);
        if (!valido) {
            addMessage("Deve ser informado Material de Consumo ou Equipamento!", FacesMessage.SEVERITY_ERROR);
        }
        return valido;
    }

    @Override
    protected ICrudService<Saida, Integer> getService() {
        return this.saidaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/movimentos/saida/saidaForm.xhtml?faces-redirect=true";
    }

    public List<Equipamento> completeEquipamento(String nome) {
        return equipamentoService.findByNomeContaining(nome);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
