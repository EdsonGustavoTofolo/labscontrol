package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Entity
@Table(name = "observacoes")
public class Observacao implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Date data;
    @Column(name = "descricao", length = 255)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "categoriaObsId", referencedColumnName = "id", nullable = false)
    private CategoriaObservacao categoriaObservacao;
    @ManyToOne
    @JoinColumn(name = "ambienteId", referencedColumnName = "id")
    private Ambiente ambiente;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id")
    private Equipamento equipamento;
    @ManyToOne
    @JoinColumn(name = "categoriaEquipamentoId", referencedColumnName = "id")
    private CategoriaEquipamento categoriaEquipamento;

    public Observacao() {
    }

    public Observacao(Date data, String descricao, Ambiente ambiente, Equipamento equipamento, CategoriaEquipamento categoriaEquipamento, CategoriaObservacao categoriaObservacao) {
        this.data = data;
        this.descricao = descricao;
        this.ambiente = ambiente;
        this.equipamento = equipamento;
        this.categoriaEquipamento = categoriaEquipamento;
        this.categoriaObservacao = categoriaObservacao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public CategoriaEquipamento getCategoriaEquipamento() {
        return categoriaEquipamento;
    }

    public void setCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
        this.categoriaEquipamento = categoriaEquipamento;
    }

    public CategoriaObservacao getCategoriaObservacao() {
        return categoriaObservacao;
    }

    public void setCategoriaObservacao(CategoriaObservacao categoriaObservacao) {
        this.categoriaObservacao = categoriaObservacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observacao that = (Observacao) o;

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
