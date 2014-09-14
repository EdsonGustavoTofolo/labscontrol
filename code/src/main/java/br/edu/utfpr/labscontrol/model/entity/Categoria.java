package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {
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

    public Categoria() {

    }

    public Categoria(String descricao, String nome) {
        this.descricao = descricao;
        this.nome = nome;
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
        if (!(o instanceof Categoria)) return false;

        Categoria categoria = (Categoria) o;

        if (id != null ? !id.equals(categoria.id) : categoria.id != null) return false;

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
