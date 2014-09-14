package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by EdsonGustavo on 10/08/2014.
 */
@Entity
@Table(name = "materiais_de_consumo")
public class MaterialDeConsumo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Length(max = 45, message = "Campo Nome: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nome", length = 45, nullable = false)
    private String nome;
    @Length(max = 60, message = "Campo Descrição: Não pode ultrapassar {max} caracteres!")
    @Column(name = "descricao", length = 60)
    private String descricao;
    @Column(name = "qtdAtual", precision = 12, scale = 2)
    private BigDecimal qtdAtual;
    @Column(name = "qtdMin", precision = 12, scale = 2)
    private BigDecimal qtdMin;

    public MaterialDeConsumo() {
        this.qtdAtual = BigDecimal.ZERO;
        this.qtdMin = BigDecimal.ZERO;
    }

    public MaterialDeConsumo(String nome, String descricao, BigDecimal qtdAtual, BigDecimal qtdMin) {
        this.nome = nome;
        this.descricao = descricao;
        this.qtdAtual = qtdAtual;
        this.qtdMin = qtdMin;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(BigDecimal qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public BigDecimal getQtdMin() {
        return qtdMin;
    }

    public void setQtdMin(BigDecimal qtdMin) {
        this.qtdMin = qtdMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialDeConsumo)) return false;

        MaterialDeConsumo that = (MaterialDeConsumo) o;

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
        return "MaterialDeConsumo{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
