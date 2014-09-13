package br.edu.utfpr.labscontrol.model.entity;

import br.edu.utfpr.labscontrol.model.enumeration.TiposDeContatoEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "contatos")
public class Contato implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String conteudo;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipoDeContato", nullable = false)
    private TiposDeContatoEnum tipoDeContato;
    @NotNull(message = "Campo Fornecedor: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "fornecedorId", referencedColumnName = "id", nullable = false)
    private Fornecedor fornecedor;
    @Length(max = 255, message = "Campo Observação: Preenchimento Obrigatório!")
    @Column(name = "observacao", length = 255)
    private String observacao;

    public Contato() {

    }

    public Contato(String conteudo, TiposDeContatoEnum tipoDeContato, Fornecedor fornecedor, String observacao) {
        this.conteudo = conteudo;
        this.tipoDeContato = tipoDeContato;
        this.fornecedor = fornecedor;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public TiposDeContatoEnum getTipoDeContato() {
        return tipoDeContato;
    }

    public void setTipoDeContato(TiposDeContatoEnum tipoDeContato) {
        this.tipoDeContato = tipoDeContato;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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
        if (!(o instanceof Contato)) return false;

        Contato contato = (Contato) o;

        if (id != null ? !id.equals(contato.id) : contato.id != null) return false;

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
        return "Contato{" +
                "conteudo='" + conteudo + '\'' +
                '}';
    }
}
