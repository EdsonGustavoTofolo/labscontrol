package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private EmprestimoItemService emprestimoItemService;
    @Autowired
    private SolicitanteService solicitanteService;

    private String tipo;
    private MaterialDeConsumo materialDeConsumo;
    private Equipamento equipamento;
    private BigDecimal qtdEstoque;
    private BigDecimal quantidade;
    private Boolean pesquisandoPorSolicitante;
    private Solicitante solicitantePesquisado;

    @Override
    protected ICrudService<Emprestimo, Integer> getService() {
        return emprestimoService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/emprestimo/emprestimoForm.xhtml?faces-redirect=true";
    }

    @Override
    protected void postCreate() {
        super.postCreate();
        this.entity.setUsuario(JsfUtil.getUsuarioLogado());
        this.qtdEstoque = BigDecimal.ZERO;
        this.pesquisandoPorSolicitante = Boolean.FALSE;
    }

    public void addItem() {
        try {
            validaQuantidadeEmEstoque();
            if (this.entity.getEmprestimoItens() == null) {
                this.entity.setEmprestimoItens(new ArrayList<>());
            }
            EmprestimoItem emprestimoItem = new EmprestimoItem();
            emprestimoItem.setEmprestimo(this.entity);
            emprestimoItem.setBaixado(Boolean.FALSE);
            emprestimoItem.setQuantidade(this.quantidade);
            if (this.tipo.equals("M")) {
                emprestimoItem.setMaterialDeConsumo(this.materialDeConsumo);
            } else {
                emprestimoItem.setEquipamento(this.equipamento);
            }
            this.entity.getEmprestimoItens().add(emprestimoItem);

            this.materialDeConsumo = null;
            this.equipamento = null;
            this.quantidade = BigDecimal.ZERO;
        } catch (IllegalArgumentException e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void excluirItem(EmprestimoItem emprestimoItem) {
        if (emprestimoItem.getMaterialDeConsumo() != null && emprestimoItem.getId() != null && emprestimoItem.getId() > 0) {
            MaterialDeConsumo m = emprestimoItem.getMaterialDeConsumo();
            m.setQtdAtual(m.getQtdAtual().add(emprestimoItem.getQuantidade()));
            try {
                materialDeConsumoService.save(m);
            } catch (Exception e) {
                addMessage("Erro ao estornar estoque!", FacesMessage.SEVERITY_FATAL);
                e.printStackTrace();
            }
        }
        this.entity.getEmprestimoItens().remove(emprestimoItem);
        if (emprestimoItem.getId() != null && emprestimoItem.getId() > 0) {
            //TODO verificar pois se salvar assim da erro na hora de salver o emprestimo e se nao nao exclui o item
            emprestimoItemService.deleteEmprestimoItemById(emprestimoItem.getId());
        }
    }

    public void onEdit(RowEditEvent event) {
        EmprestimoItem item = (EmprestimoItem) event.getObject();
        try {
            validaDataDeDevolucao(item);
            validaQuantidadeBaixada(item);
            if (item.getMaterialDeConsumo() != null) {
                estornarQuantidadeBaixada(item);
            }
            emprestimoItemService.save(item);
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    private void estornarQuantidadeBaixada(EmprestimoItem item) {
        MaterialDeConsumo m = item.getMaterialDeConsumo();
        if (item.getQuantidadeBaixada() != null) {
            m.setQtdAtual(m.getQtdAtual().add(item.getQuantidadeBaixada()));
            try {
                materialDeConsumoService.save(m);
            } catch (Exception e) {
                addMessage("Erro ao estornar estoque!", FacesMessage.SEVERITY_ERROR);
                e.printStackTrace();
            }
        }
    }

    private void validaDataDeDevolucao(EmprestimoItem item) throws IllegalArgumentException {
        if (item.getDataDevolucao() != null) {
            Long time1 = item.getEmprestimo().getData().getTime();
            Long time2 = item.getDataDevolucao().getTime();
            if (time1 > time2) {
                throw new IllegalArgumentException("Data de Devolução deve ser maior ou igual a Data do Empréstimo");
            }
        }
    }

    private void validaQuantidadeBaixada(EmprestimoItem item) throws IllegalArgumentException {
        if (item.getQuantidadeBaixada() != null && item.getQuantidade().compareTo(item.getQuantidadeBaixada()) == -1) {
            throw new IllegalArgumentException("Quantidade a ser baixada deve ser menor ou igual a Quantidade emprestada!");
        }
    }

    @Override
    public void save() {
        super.save();
        baixarEstoque();
    }

    @Override
    public void delete() {
        estornarEstoque();
        super.delete();
    }

    private void estornarEstoque() {
        baixarOrEstornar(Boolean.FALSE);
    }

    private void baixarEstoque() {
        baixarOrEstornar(Boolean.TRUE);
    }

    private void baixarOrEstornar(Boolean baixar) {
        if (this.entity.getEmprestimoItens() != null) {
            for (EmprestimoItem ei : this.entity.getEmprestimoItens()) {
                if (ei.getMaterialDeConsumo() != null) {
                    MaterialDeConsumo m = ei.getMaterialDeConsumo();
                    if (baixar) {
                        m.setQtdAtual(m.getQtdAtual().subtract(ei.getQuantidade()));
                    } else {
                        m.setQtdAtual(m.getQtdAtual().add(ei.getQuantidade()));
                    }
                    try {
                        materialDeConsumoService.save(m);
                    } catch (Exception e) {
                        addMessage("Erro ao baixar/estornar estoque!", FacesMessage.SEVERITY_FATAL);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void validaQuantidadeEmEstoque() throws IllegalArgumentException {
        if (this.tipo.equals("M")) {
            if (this.materialDeConsumo.getQtdAtual().subtract(this.quantidade, MathContext.DECIMAL64).compareTo(BigDecimal.ZERO) == -1) {
                throw new IllegalArgumentException("Não há quantidade em estoque o suficiente!");
            }
        }
    }

    public List<Equipamento> completeEquipamento(String value) {
        return equipamentoService.findByNomeContainingOrPatrimonioContaining(value, value);
    }

    public List<Solicitante> completeSolicitante(String value) {
        return solicitanteService.findByNomeContainingOrIdentificacaoContaining(value, value);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    public void onSolicitanteSelect(SelectEvent event) {
        this.pesquisandoPorSolicitante = Boolean.TRUE;
        Object o = event.getObject();
        if (o instanceof Solicitante) {
            this.lsEntity.clear();
            this.lsEntity.addAll(this.emprestimoService.findByPendenciasDoSolicitanteId(((Solicitante) o).getId()));
        }
    }

    @Override
    public void find() {
        if (this.pesquisandoPorSolicitante) {
            this.lsEntity.clear();
            this.lsEntity.addAll(this.emprestimoService.findByPendenciasDoSolicitanteId(solicitantePesquisado.getId()));
            this.pesquisandoPorSolicitante = Boolean.FALSE;
        } else if (!FacesContext.getCurrentInstance().isPostback()) { // Evita que nas chamadas ajax seja executado o find()
            super.find();
        }
    }

    public void buscarTodos() {
        super.find();
    }

    public void onItemSelect(SelectEvent event) {
        Object o = event.getObject();
        if (o instanceof MaterialDeConsumo) {
            MaterialDeConsumo m = materialDeConsumoService.findById(((MaterialDeConsumo)o).getId());
            this.qtdEstoque = m.getQtdAtual();
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public MaterialDeConsumo getMaterialDeConsumo() {
        return materialDeConsumo;
    }

    public void setMaterialDeConsumo(MaterialDeConsumo materialDeConsumo) {
        this.materialDeConsumo = materialDeConsumo;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public BigDecimal getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(BigDecimal qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Solicitante getSolicitantePesquisado() {
        return solicitantePesquisado;
    }

    public void setSolicitantePesquisado(Solicitante solicitantePesquisado) {
        this.solicitantePesquisado = solicitantePesquisado;
    }
}
