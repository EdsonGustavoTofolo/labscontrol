package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.entity.Saida;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.model.service.SaidaService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
