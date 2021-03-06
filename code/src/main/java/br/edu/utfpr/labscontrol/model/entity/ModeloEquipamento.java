package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Entity
@Table(name = "modelos_equipamentos")
public class ModeloEquipamento implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(name = "nome", length = 45, nullable = false)
    private String nome;
    @Column(name = "descricao", length = 60)
    private String descricao;

    public ModeloEquipamento(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
    }

    public ModeloEquipamento() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModeloEquipamento that = (ModeloEquipamento) o;

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
        return nome;
    }
}
