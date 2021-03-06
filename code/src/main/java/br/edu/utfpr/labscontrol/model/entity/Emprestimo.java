package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
@Entity
@Table(name = "emprestimos")
public class Emprestimo implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", referencedColumnName = "id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "solicitanteId", referencedColumnName = "id", nullable = false)
    private Solicitante solicitante;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;
    @OneToMany(mappedBy = "emprestimo", targetEntity = EmprestimoItem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<EmprestimoItem> emprestimoItens;
    @Column(length = 255)
    private String observacao;

    public Emprestimo(Usuario usuario, Solicitante solicitante, Date data, List<EmprestimoItem> emprestimoItens, String observacao) {
        this.usuario = usuario;
        this.solicitante = solicitante;
        this.data = data;
        this.emprestimoItens = emprestimoItens;
        this.observacao = observacao;
    }

    public Emprestimo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<EmprestimoItem> getEmprestimoItens() {
        return emprestimoItens;
    }

    public void setEmprestimoItens(List<EmprestimoItem> emprestimoItens) {
        this.emprestimoItens = emprestimoItens;
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
        if (o == null || getClass() != o.getClass()) return false;

        Emprestimo emprestimo = (Emprestimo) o;

        if (id != null ? !id.equals(emprestimo.id) : emprestimo.id != null) return false;

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
