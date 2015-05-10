package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EmprestimoService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
@Controller
@Scope("view")
public class EmprestimoController extends CrudController<Emprestimo, Integer> {
    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;

    @Override
    protected ICrudService<Emprestimo, Integer> getService() {
        return emprestimoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/emprestimo/emprestimoForm.xhtml?faces-redirect=true";
    }

    public List<Equipamento> completeEquipamento(String nome) {
        return equipamentoService.findByNomeContaining(nome);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    //TODO baixar em estoque automaticamente
}
