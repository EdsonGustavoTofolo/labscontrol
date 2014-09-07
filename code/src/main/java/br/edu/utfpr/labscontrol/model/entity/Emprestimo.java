package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "emprestimos")
public class Emprestimo implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "Campo Data de Empréstimo: Preenchimento Obrigatório!")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataEmprestimo", nullable = false)
    private Calendar dataEmprestimo;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataDevolPrevista")
    private Calendar dataDevolPrevista;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataDevolEfetiva")
    private Calendar dataDevolEfetiva;
    @NotNull(message = "Campo De usuário: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "fromUsuarioId", referencedColumnName = "id", nullable = false)
    private Usuario fromUsuario;
    @NotNull(message = "Campo Para usuário: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "toUsuarioId", referencedColumnName = "id", nullable = false)
    private Usuario toUsuario;
    @NotNull(message = "Campo Material: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "materialId", referencedColumnName = "id")
    private Material material;
    @NotNull(message = "Campo Ambiente: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "ambienteId", referencedColumnName = "id")
    private Ambiente ambiente;
    @Length(max = 255, message = "Campo Observação: Não pode ultrapassar {max} caracteres!")
    @Column(name = "observacao", length = 255)
    private String observacao;

    public Emprestimo() {

    }

    public Emprestimo(Calendar dataEmprestimo, Calendar dataDevolPrevista, Calendar dataDevolEfetiva, Usuario fromUsuario,
                      Usuario toUsuario, Material material, Ambiente ambiente, String observacao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolPrevista = dataDevolPrevista;
        this.dataDevolEfetiva = dataDevolEfetiva;
        this.fromUsuario = fromUsuario;
        this.toUsuario = toUsuario;
        this.material = material;
        this.ambiente = ambiente;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Calendar getDataDevolPrevista() {
        return dataDevolPrevista;
    }

    public void setDataDevolPrevista(Calendar dataDevolPrevista) {
        this.dataDevolPrevista = dataDevolPrevista;
    }

    public Calendar getDataDevolEfetiva() {
        return dataDevolEfetiva;
    }

    public void setDataDevolEfetiva(Calendar dataDevolEfetiva) {
        this.dataDevolEfetiva = dataDevolEfetiva;
    }

    public Usuario getFromUsuario() {
        return fromUsuario;
    }

    public void setFromUsuario(Usuario fromUsuario) {
        this.fromUsuario = fromUsuario;
    }

    public Usuario getToUsuario() {
        return toUsuario;
    }

    public void setToUsuario(Usuario toUsuario) {
        this.toUsuario = toUsuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
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
        if (!(o instanceof Emprestimo)) return false;

        Emprestimo that = (Emprestimo) o;

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
        return "Emprestimo{" +
                "dataEmprestimo=" + dataEmprestimo +
                ", fromUsuario=" + fromUsuario +
                ", toUsuario=" + toUsuario +
                ", material=" + material +
                ", ambiente=" + ambiente +
                '}';
    }
}
