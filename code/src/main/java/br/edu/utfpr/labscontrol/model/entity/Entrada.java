package br.edu.utfpr.labscontrol.model.entity;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Entity
@Table(name = "entradas")
public class Entrada implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Date data;
    @Column(name = "quantidade", precision = 12, scale = 2)
    private BigDecimal quantidade;
    @ManyToOne
    @JoinColumn(name = "fornecedorId", referencedColumnName = "id", nullable = false)
    private Fornecedor fornecedor;
    @ManyToOne
    @JoinColumn(name = "materialDeConsumoId", referencedColumnName = "id")
    private MaterialDeConsumo materialDeConsumo;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id")
    private Equipamento equipamento;

    public Entrada(Date data, BigDecimal quantidade, Fornecedor fornecedor, MaterialDeConsumo materialDeConsumo, Equipamento equipamento) {
        this.data = data;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.materialDeConsumo = materialDeConsumo;
        this.equipamento = equipamento;
    }

    public Entrada() {
        this.quantidade = BigDecimal.ZERO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entrada entrada = (Entrada) o;

        if (id != null ? !id.equals(entrada.id) : entrada.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "id=" + id +
                '}';
    }
}
