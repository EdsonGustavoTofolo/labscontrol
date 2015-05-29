package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Entity
@Table(name = "estados")
public class Estado implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "nome", length = 80, nullable = false, unique = true)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "paisId", referencedColumnName = "id", nullable = false)
    private Pais pais;

    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        if (id != null ? !id.equals(estado.id) : estado.id != null) return false;

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
