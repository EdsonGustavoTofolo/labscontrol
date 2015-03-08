package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by EdsonGustavo on 10/08/2014.
 */
@Entity
@Table(name = "historicos_de_manutencoes")
public class HistoricoDeManutencao implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "motivoDoDefeito", length = 100, nullable = false)
    private String motivoDoDefeito;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataDoDefeito", nullable = false)
    private Date dataDoDefeito;
    @Temporal(TemporalType.DATE)
    private Date dataDeEnvio;
    @Temporal(TemporalType.DATE)
    private Date dataDeRetorno;
    @Column(name = "manutencaoRealizada", length = 255)
    private String manutencaoRealizada;
    @Column(name = "valorDaManutencao", precision = 12, scale = 2)
    private BigDecimal valorDaManutencao;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id", nullable = false)
    private Equipamento equipamento;

    public HistoricoDeManutencao() {
        this.valorDaManutencao = BigDecimal.ZERO;
    }

    public HistoricoDeManutencao(String motivoDoDefeito, Date dataDoDefeito, Date dataDeEnvio, Date dataDeRetorno,
                                 String manutencaoRealizada, BigDecimal valorDaManutencao, Equipamento equipamento) {
        this.motivoDoDefeito = motivoDoDefeito;
        this.dataDoDefeito = dataDoDefeito;
        this.dataDeEnvio = dataDeEnvio;
        this.dataDeRetorno = dataDeRetorno;
        this.manutencaoRealizada = manutencaoRealizada;
        this.valorDaManutencao = valorDaManutencao;
        this.equipamento = equipamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivoDoDefeito() {
        return motivoDoDefeito;
    }

    public void setMotivoDoDefeito(String motivoDoDefeito) {
        this.motivoDoDefeito = motivoDoDefeito;
    }

    public Date getDataDoDefeito() {
        return dataDoDefeito;
    }

    public void setDataDoDefeito(Date dataDoDefeito) {
        this.dataDoDefeito = dataDoDefeito;
    }

    public Date getDataDeEnvio() {
        return dataDeEnvio;
    }

    public void setDataDeEnvio(Date dataDeEnvio) {
        this.dataDeEnvio = dataDeEnvio;
    }

    public Date getDataDeRetorno() {
        return dataDeRetorno;
    }

    public void setDataDeRetorno(Date dataDeRetorno) {
        this.dataDeRetorno = dataDeRetorno;
    }

    public String getManutencaoRealizada() {
        return manutencaoRealizada;
    }

    public void setManutencaoRealizada(String manutencaoRealizada) {
        this.manutencaoRealizada = manutencaoRealizada;
    }

    public BigDecimal getValorDaManutencao() {
        return valorDaManutencao;
    }

    public void setValorDaManutencao(BigDecimal valorDaManutencao) {
        this.valorDaManutencao = valorDaManutencao;
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
        if (!(o instanceof HistoricoDeManutencao)) return false;

        HistoricoDeManutencao that = (HistoricoDeManutencao) o;

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

    @Override
    public String toString() {
        return "HistoricoDeManutencao{" +
                "motivoDoDefeito='" + motivoDoDefeito + '\'' +
                '}';
    }
}
