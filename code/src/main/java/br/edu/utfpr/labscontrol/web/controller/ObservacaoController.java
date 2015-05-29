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
 * Created by EdsonGustavo on 29/05/2015.
 */
@Controller
@Scope("view")
public class ObservacaoController extends CrudController<Observacao, Integer> {
    @Autowired
    private ObservacaoService observacaoService;
    @Autowired
    private CategoriaObservacaoService categoriaObservacaoService;
    @Autowired
    private AmbienteService ambienteService;
    @Autowired
    private EquipamentoService equipamentoService;
    @Autowired
    private CategoriaEquipamentoService categoriaEquipamentoService;

    @Override
    protected ICrudService<Observacao, Integer> getService() {
        return observacaoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/observacao/observacaoForm.xhtml?faces-redirect=true";
    }

    public List<CategoriaObservacao> completeCategoriaObs(String descricao) {
        return categoriaObservacaoService.findByDescricaoContaining(descricao);
    }

    public List<Ambiente> completeAmbiente(String descricao) {
        return ambienteService.findByDescricaoContaining(descricao);
    }

    public List<Equipamento> completeEquipamento(String nome) {
        return equipamentoService.findByNomeContaining(nome);
    }

    public List<CategoriaEquipamento> completeCategoriaEquipamento(String nome) {
        return categoriaEquipamentoService.findByNomeContaining(nome);
    }
}
