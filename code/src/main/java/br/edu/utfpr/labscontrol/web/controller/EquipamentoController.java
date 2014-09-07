package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.HistoricoDeManutencao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import br.edu.utfpr.labscontrol.model.service.HistoricoDeManutencaoService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;

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

    private HistoricoDeManutencao historicoDeManutencao;

    @Override
    protected ICrudService<Equipamento, Integer> getService() {
        return this.equipamentoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/cadastros/equipamento/equipamentoForm.xhtml?faces-redirect=true";
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
            save();
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

    public void setEntityEmbedded(HistoricoDeManutencao historicoDeManutencao) {
        this.historicoDeManutencao = historicoDeManutencao;
    }

    public HistoricoDeManutencao getEntityEmbedded() {
        return this.historicoDeManutencao;
    }
}
