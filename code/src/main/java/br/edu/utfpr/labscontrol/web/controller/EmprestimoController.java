package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
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
    @Autowired private EmprestimoService emprestimoService;
    @Autowired private EquipamentoService equipamentoService;
    @Autowired private MaterialDeConsumoService materialDeConsumoService;
    @Autowired private EmprestimoItemService emprestimoItemService;
    @Autowired private SolicitanteService solicitanteService;
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
    public String getUrlSearchPage() {
        return "/pages/emprestimo/emprestimoSearch.xhtml?faces-redirect=true";
    }

    @Override
    protected void postCreate() {
        super.postCreate();
        this.entity.setUsuario(JsfUtil.getUsuarioLogado());
        this.qtdEstoque = BigDecimal.ZERO;
        this.quantidade = BigDecimal.ZERO;
        this.pesquisandoPorSolicitante = Boolean.FALSE;
        this.entity.setData(Calendar.getInstance().getTime());
        this.materialDeConsumo = null;
        this.equipamento = null;
        this.tipo = "";
        this.entity.setSolicitante(null);
    }

    @Override
    public void reset() {
        super.reset();
    }

    private void validaItem() throws IllegalArgumentException {
        if (this.tipo.equals("M") && this.materialDeConsumo == null) {
            throw new IllegalArgumentException("Informe um Material de Consumo válido!");
        } else if (this.tipo.equals("E") && this.equipamento == null) {
            throw new IllegalArgumentException("Informe um Equipamento válido!");
        }
    }

    public void addItem() {
        try {
            validaCabecalho();
            validaItem();
            validaQuantidadeEmEstoque();

            EmprestimoItem emprestimoItem = new EmprestimoItem();
            emprestimoItem.setEmprestimo(this.entity);
            emprestimoItem.setBaixado(Boolean.FALSE);
            emprestimoItem.setQuantidade(this.quantidade);
            if (this.tipo.equals("M")) {
                emprestimoItem.setMaterialDeConsumo(this.materialDeConsumo);
            } else {
                emprestimoItem.setEquipamento(this.equipamento);
            }
            emprestimoItem.setQuantidadeBaixada(BigDecimal.ZERO);
            emprestimoItem.setBaixado(Boolean.FALSE);

            if (this.entity.getEmprestimoItens() == null) {
                this.entity.setEmprestimoItens(new ArrayList<>());
            }
            this.entity.getEmprestimoItens().add(emprestimoItem);
            this.emprestimoItemService.save(emprestimoItem);

            this.materialDeConsumo = null;
            this.equipamento = null;
            this.quantidade = BigDecimal.ZERO;
            this.qtdEstoque = BigDecimal.ZERO;
//            RequestContext.getCurrentInstance().execute("PrimeFaces.focus(inputId);"); TODO setar o foco no componente que está habilidado: Material de consumo ou equipamento
        } catch (Exception e) {
            addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    private void validaCabecalho() throws Exception {
        if (this.entity.getId() == null) {
            if (this.entity.getSolicitante() == null || this.entity.getData() == null) {
                throw new IllegalArgumentException("Informe os dados do cabeçalho antes de inserir um item!");
            } else {
                getService().save(this.entity);
            }
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
                item.setDataDevolucao(null);
                throw new IllegalArgumentException("Data de Devolução deve ser maior ou igual a Data do Empréstimo");
            }
        }
    }

    private void validaQuantidadeBaixada(EmprestimoItem item) throws IllegalArgumentException {
        if (item.getQuantidadeBaixada() != null && item.getQuantidade().compareTo(item.getQuantidadeBaixada()) == -1) {
            item.setQuantidadeBaixada(BigDecimal.ZERO);
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
        estornarEstoque(this.emprestimoService.findById(getId()));
        super.delete();
    }

    @Override
    public void deleteFromForm() {
        estornarEstoque(this.entity);
        super.deleteFromForm();
    }

    private void estornarEstoque(Emprestimo emprestimo) {
        baixarOrEstornar(Boolean.FALSE, emprestimo);
    }

    private void baixarEstoque() {
        baixarOrEstornar(Boolean.TRUE, this.entity);
    }

    private void baixarOrEstornar(Boolean baixar, Emprestimo emprestimo) {
        if (emprestimo.getEmprestimoItens() != null) {
            for (EmprestimoItem ei : emprestimo.getEmprestimoItens()) {
                if (ei.getMaterialDeConsumo() != null) {
                    MaterialDeConsumo m = ei.getMaterialDeConsumo();
                    if (baixar) {
                        m.setQtdAtual(m.getQtdAtual().subtract(ei.getQuantidade()));
                    } else
                    // Se ainda não foi dado Baixa e se existe quantidade baixada e a mesma é menor que a quantidade emprestada
                    if (!ei.getBaixado() && ((ei.getQuantidadeBaixada() == null) || (ei.getQuantidadeBaixada() != null && ei.getQuantidadeBaixada().compareTo(ei.getQuantidade()) == -1))) {
                        //Pega a diferença entre a quantidade emprestada e a quantidade baixada
                        BigDecimal qtdDiff = ei.getQuantidade().subtract( ei.getQuantidadeBaixada() == null ? BigDecimal.ZERO : ei.getQuantidadeBaixada() ) ;
                        m.setQtdAtual(m.getQtdAtual().add(qtdDiff));
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
            } else if (this.quantidade.compareTo(BigDecimal.ZERO) == 0) {
                RequestContext.getCurrentInstance().execute("markErrorAndSetFocus('form:quantidade')");
                throw new IllegalArgumentException("Informe uma quantidade!");
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
    }

    @Override
    public void find() {
        if (this.pesquisandoPorSolicitante) {
            this.lsEntity.clear();
            this.lsEntity.addAll(this.emprestimoService.findByPendenciasDoSolicitanteId(solicitantePesquisado.getId()));
            this.pesquisandoPorSolicitante = Boolean.FALSE;
        } else if (!FacesContext.getCurrentInstance().isPostback() || (getId() != null && getId() > 0)) { // Evita que nas chamadas ajax seja executado o find()
            super.find();
        }
    }

    public void buscarTodos() {
        this.solicitantePesquisado = null;
        super.find();
    }

    public void onItemSelect(SelectEvent event) {
        Object o = event.getObject();
        if (o instanceof MaterialDeConsumo) {
            MaterialDeConsumo m = materialDeConsumoService.findById(((MaterialDeConsumo)o).getId());
            this.qtdEstoque = m.getQtdAtual();
        }
    }

    public void onEquipamentoSelect(SelectEvent event) {
        Object o = event.getObject();
        if (o instanceof Equipamento) {
            this.quantidade = BigDecimal.ONE;
//            addItem();
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
