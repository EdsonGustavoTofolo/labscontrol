package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Entity
@Table(name = "cidades")
public class Cidade implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "nome", length = 80, nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "estadoId", referencedColumnName = "id", nullable = false)
    private Estado estado;

    public Cidade() {
    }

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cidade cidade = (Cidade) o;

        if (id != null ? !id.equals(cidade.id) : cidade.id != null) return false;

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
