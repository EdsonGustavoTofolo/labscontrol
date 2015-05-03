package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
@Entity
@Table(name = "emprestimos_itens")
public class EmprestimoItem implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "materialDeConsumoId", referencedColumnName = "id")
    private MaterialDeConsumo materialDeConsumo;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id")
    private Equipamento equipamento;
    @Column(name = "quantidade", precision = 12, scale = 2)
    private BigDecimal quantidade;
    @Column(name = "quantidade", precision = 12, scale = 2)
    private BigDecimal quantidadeBaixada;
    @Column
    private Boolean baixado;
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    @ManyToOne
    @JoinColumn(name = "emprestimoId", referencedColumnName = "id")
    private Emprestimo emprestimo;

    public EmprestimoItem(MaterialDeConsumo materialDeConsumo, Equipamento equipamento, BigDecimal quantidade, BigDecimal quantidadeBaixada, Boolean baixado, Date dataDevolucao, Emprestimo emprestimo) {
        this.materialDeConsumo = materialDeConsumo;
        this.equipamento = equipamento;
        this.quantidade = quantidade;
        this.quantidadeBaixada = quantidadeBaixada;
        this.baixado = baixado;
        this.dataDevolucao = dataDevolucao;
        this.emprestimo = emprestimo;
    }

    public EmprestimoItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getQuantidadeBaixada() {
        return quantidadeBaixada;
    }

    public void setQuantidadeBaixada(BigDecimal quantidadeBaixada) {
        this.quantidadeBaixada = quantidadeBaixada;
    }

    public Boolean getBaixado() {
        return baixado;
    }

    public void setBaixado(Boolean baixado) {
        this.baixado = baixado;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmprestimoItem that = (EmprestimoItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
