package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 13/06/2015.
 */
@Entity
@Table(name = "solicitantes")
public class Solicitante implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(name = "nome", length = 80, nullable = false)
    private String nome;
    @Column(name = "identificacao", nullable = false, unique = true)
    private String identificacao;
    @Column(name = "telefone", length = 14)
    private String telefone;
    @Column(name = "email")
    private String email;

    public Solicitante() {
    }

    public Solicitante(String nome, String identificacao, String telefone, String email) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.telefone = telefone;
        this.email = email;
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

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solicitante that = (Solicitante) o;

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
