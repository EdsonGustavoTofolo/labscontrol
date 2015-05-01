package br.edu.utfpr.labscontrol.model.entity;

import br.edu.utfpr.labscontrol.model.enumeration.UFsEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @Column(name = "razaoSocial", length = 80, nullable = false)
    private String razaoSocial;
    @Column(name = "nomeFantasia", length = 60, nullable = false)
    private String nomeFantasia;
    @Column(name = "nomeDoContato", length = 60)
    private String nomeDoContato;
    @Column(name = "cnpj", length = 18)
    private String cnpj;
    @Column(name = "logradouro", length = 45)
    private String logradouro;
    @Column(name = "numero", length = 20)
    private String numero;
    @Column(name = "bairro", length = 45)
    private String bairro;
    @Column(name = "cidade", length = 45)
    private String cidade;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 2)
    private UFsEnum estado;
    @Column(name = "cep", length = 10)
    private String cep;
    @Column(name = "observacao", length = 255)
    private String observacao;
    @OneToMany(mappedBy = "fornecedor", orphanRemoval = true, targetEntity = Contato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Contato> contatos;

    public Fornecedor() {

    }

    public Fornecedor(String razaoSocial, String nomeFantasia, String nomeDoContato, List<Contato> contatos) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.nomeDoContato = nomeDoContato;
        this.contatos = contatos;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public UFsEnum getEstado() {
        return estado;
    }

    public void setEstado(UFsEnum estado) {
        this.estado = estado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
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
        return razaoSocial;
    }
}
