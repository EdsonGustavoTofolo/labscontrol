package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by EdsonGustavo on 06/12/2014.
 */
@Entity
@Table(name = "reservas_itens")
public class ReservaItem implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "quantidade", precision = 12, scale = 2)
    private BigDecimal quantidade;
    @ManyToOne
    @JoinColumn(name = "materialDeConsumoId", referencedColumnName = "id")
    private MaterialDeConsumo materialDeConsumo;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id")
    private Equipamento equipamento;
    @ManyToOne
    @JoinColumn(name = "reservaId", referencedColumnName = "id")
    private Reserva reserva;

    public ReservaItem() {
    }

    public ReservaItem(BigDecimal quantidade, MaterialDeConsumo materialDeConsumo, Equipamento equipamento, Reserva reserva) {
        this.quantidade = quantidade;
        this.materialDeConsumo = materialDeConsumo;
        this.equipamento = equipamento;
        this.reserva = reserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservaItem that = (ReservaItem) o;

        if (id == null && that.getId() == null) {
            return this == that;
        }

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
