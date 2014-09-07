package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "tipos_de_contatos")
public class TipoDeContato implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Campo Descricão: Preenchimento Obrigatório!")
    @Length(max = 45, message = "Campo Descrição: Não pode ultrapassar {max} caracteres!")
    @Column(name = "descricao", length = 45, nullable = false)
    private String descricao;

    public TipoDeContato() {

    }

    public TipoDeContato(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoDeContato)) return false;

        TipoDeContato that = (TipoDeContato) o;

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
        return descricao;
    }
}
