package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "equipamentos")
public class Equipamento implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Campo Nome: Preenchimento Obrigatório!")
    @Length(max = 45, message = "Campo Nome: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nome", length = 45, nullable = false)
    private String nome;
    @Length(max = 45, message = "Campo Código de Barras: Não pode ultrapassar {max} caracteres!")
    @Column(name = "codigoDeBarras", length = 45)
    private String codigoDeBarras;
    @Length(max = 45, message = "Campo Patrimônio: Não pode ultrapassar {max} caracteres!")
    @Column(name = "patrimonio", length = 45)
    private String patrimonio;
    @Length(max = 45, message = "Campo Part Number: Não pode ultrapassar {max} caracteres!")
    @Column(name = "partNumber", length = 45)
    private String partNumber;
    @Temporal(TemporalType.DATE)
    private Date dataDeAquisicao;
    @Length(max = 255, message = "Campo Observação: Não pode ultrapassar {max} caracteres!")
    @Column(name = "observacao", length = 255)
    private String observacao;
    @OneToMany(mappedBy = "equipamento", orphanRemoval = true, targetEntity = HistoricoDeManutencao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<HistoricoDeManutencao> historicoDeManutencoes;

    public Equipamento() {

    }

    public Equipamento(String nome, String codigoDeBarras, String patrimonio, String partNumber, Date dataDeAquisicao, String observacao, List<HistoricoDeManutencao> historicoDeManutencoes) {
        this.nome = nome;
        this.codigoDeBarras = codigoDeBarras;
        this.patrimonio = patrimonio;
        this.partNumber = partNumber;
        this.dataDeAquisicao = dataDeAquisicao;
        this.observacao = observacao;
        this.historicoDeManutencoes = historicoDeManutencoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Date getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(Date data) {
        this.dataDeAquisicao = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<HistoricoDeManutencao> getHistoricoDeManutencoes() {
        return historicoDeManutencoes;
    }

    public void setHistoricoDeManutencoes(List<HistoricoDeManutencao> historicoDeManutencoes) {
        this.historicoDeManutencoes = historicoDeManutencoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipamento)) return false;

        Equipamento that = (Equipamento) o;

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
        return "Equipamento{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
