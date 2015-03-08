package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Entity
@Table(name = "saidas")
public class Saida implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Date data;
    @Column(name = "observacao", length = 255)
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "materialDeConsumoId", referencedColumnName = "id")
    private MaterialDeConsumo materialDeConsumo;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id")
    private Equipamento equipamento;

    public Saida(Date data, String observacao, MaterialDeConsumo materialDeConsumo, Equipamento equipamento) {
        this.data = data;
        this.observacao = observacao;
        this.materialDeConsumo = materialDeConsumo;
        this.equipamento = equipamento;
    }

    public Saida() {

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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

        Saida saida = (Saida) o;

        if (id != null ? !id.equals(saida.id) : saida.id != null) return false;

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
        return "Saida{" +
                "id=" + id +
                ", data=" + data +
                ", observacao='" + observacao + '\'' +
                ", materialDeConsumo=" + materialDeConsumo +
                ", equipamento=" + equipamento +
                '}';
    }
}
