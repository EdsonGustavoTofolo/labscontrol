package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "Campo Razão Social: Preenchimento Obrigatório!")
    @Length(max = 80, message = "Campo Razão Social: Preenchimento Obrigatório!")
    @Column(name = "razaoSocial", length = 80, nullable = false)
    private String razaoSocial;
    @NotEmpty(message = "Campo Nome Fantasia: Preenchimento Obrigatório!")
    @Length(max = 60, message = "Campo Nome Fantasia: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nomeFantasia", length = 60, nullable = false)
    private String nomeFantasia;
    @Length(max = 60, message = "Campo Nome do contato: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nomeDoContato", length = 60)
    private String nomeDoContato;

    public Fornecedor() {

    }

    public Fornecedor(String razaoSocial, String nomeFantasia, String nomeDoContato) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.nomeDoContato = nomeDoContato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNomeDoContato() {
        return nomeDoContato;
    }

    public void setNomeDoContato(String nomeDoContato) {
        this.nomeDoContato = nomeDoContato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fornecedor)) return false;

        Fornecedor that = (Fornecedor) o;

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
        return "Fornecedor{" +
                "razaoSocial='" + razaoSocial + '\'' +
                '}';
    }
}
