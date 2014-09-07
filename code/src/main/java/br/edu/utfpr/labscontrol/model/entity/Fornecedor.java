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
    @Length(max = 80, message = "Campo Razão Social: Preenchimento Obrigatório!")
    @Column(name = "razaoSocial", length = 80, nullable = false)
    private String razaoSocial;
    @Length(max = 60, message = "Campo Nome Fantasia: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nomeFantasia", length = 60, nullable = false)
    private String nomeFantasia;
    @Length(max = 60, message = "Campo Nome do contato: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nomeDoContato", length = 60)
    private String nomeDoContato;
    @Length(max = 45, message = "Campo Logradouro: Não pode ultrapassar {max} caracteres!")
    @Column(name = "logradouro", length = 45)
    private String logradouro;
    @Length(max = 20, message = "Campo Número: Não pode ultrapassar {max} caracteres!")
    @Column(name = "numero", length = 20)
    private String numero;
    @Length(max = 45, message = "Campo Bairro: Não pode ultrapassar {max} caracteres!")
    @Column(name = "bairro", length = 45)
    private String bairro;
    @Length(max = 45, message = "Campo Cidade: Não pode ultrapassar {max} caracteres!")
    @Column(name = "cidade", length = 45)
    private String cidade;
    @Column(name = "estado", length = 2)
    private String estado;
    @Column(name = "cep", length = 10)
    private String cep;
    @Length(max = 255, message = "Campo Observação: Não pode ultrapassar {max} caracteres!")
    @Column(name = "observacao", length = 255)
    private String observacao;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
