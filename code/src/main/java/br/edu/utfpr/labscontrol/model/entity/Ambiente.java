package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "ambientes")
public class Ambiente implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Length(max = 20, message = "Campo Identificação: Não pode ultrapassar {max} caracteres!")
    @Column(name = "identificacao", length = 20, nullable = false)
    private String identificacao;
    @Length(max = 100, message = "Campo Descrição: Não pode ultrapassar {max} caracteres!")
    @Column(name = "descricao", length = 100)
    private String descricao;
    @Length(max = 255, message = "Campo Observação: Não pode ultrapassar {max} caracteres!")
    @Column(name = "observacao", length = 255)
    private String observacao;

    public Ambiente() {

    }

    public Ambiente(String identificacao, String descricao, String observacao) {
        this.identificacao = identificacao;
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ambiente)) return false;

        Ambiente ambiente = (Ambiente) o;

        if (id != null ? !id.equals(ambiente.id) : ambiente.id != null) return false;

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
        return "Ambiente{" +
                "identificacao='" + identificacao + '\'' +
                '}';
    }
}
