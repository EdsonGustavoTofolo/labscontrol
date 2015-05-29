package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Entity
@Table(name = "categorias_observacoes")
public class CategoriaObservacao implements Serializable {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column(name = "descricao", nullable = false, unique = true, length = 45)
    private String descricao;

    public CategoriaObservacao() {
    }

    public CategoriaObservacao(String descricao) {
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
        if (o == null || getClass() != o.getClass()) return false;

        CategoriaObservacao that = (CategoriaObservacao) o;

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
