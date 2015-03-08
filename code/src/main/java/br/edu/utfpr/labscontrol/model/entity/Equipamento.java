package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "equipamentos")
public class Equipamento implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome", length = 45, nullable = false)
    private String nome;
    @Column(name = "codigoDeBarras", length = 45)
    private String codigoDeBarras;
    @Column(name = "patrimonio", length = 45)
    private String patrimonio;
    @Column(name = "partNumber", length = 45)
    private String partNumber;
    @Temporal(TemporalType.DATE)
    private Date dataDeAquisicao;
    @Column(name = "foto", length = 512)
    private String foto;
    @Column(name = "observacao", length = 255)
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "categoriaId", referencedColumnName = "id", nullable = false)
    private CategoriaEquipamento categoria;
    @ManyToOne
    @JoinColumn(name = "modeloId", referencedColumnName = "id", nullable = false)
    private ModeloEquipamento modelo;
    @ManyToOne
    @JoinColumn(name = "marcaId", referencedColumnName = "id", nullable = false)
    private MarcaEquipamento marca;
    @OneToMany(mappedBy = "equipamento", orphanRemoval = true, targetEntity = HistoricoDeManutencao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<HistoricoDeManutencao> historicoDeManutencoes;
    @ManyToOne
    @JoinColumn(name = "fornecedorId", referencedColumnName = "id", nullable = false)
    private Fornecedor fornecedor;

    public Equipamento() {

    }

    public Equipamento(String nome, String codigoDeBarras, String patrimonio, String partNumber, Date dataDeAquisicao, String observacao, List<HistoricoDeManutencao> historicoDeManutencoes) {
        this.nome = nome;
        this.codigoDeBarras = codigoDeBarras;
        this.patrimonio = patrimonio;
        this.partNumber = partNumber;
        this.dataDeAquisicao = dataDeAquisicao;
        this.observacao = observacao;
        this.historicoDeManutencoes = historicoDeManutencoes;
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

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Date getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(Date data) {
        this.dataDeAquisicao = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public CategoriaEquipamento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEquipamento categoria) {
        this.categoria = categoria;
    }

    public ModeloEquipamento getModelo() {
        return modelo;
    }

    public void setModelo(ModeloEquipamento modelo) {
        this.modelo = modelo;
    }

    public MarcaEquipamento getMarca() {
        return marca;
    }

    public void setMarca(MarcaEquipamento marca) {
        this.marca = marca;
    }

    public List<HistoricoDeManutencao> getHistoricoDeManutencoes() {
        return historicoDeManutencoes;
    }

    public void setHistoricoDeManutencoes(List<HistoricoDeManutencao> historicoDeManutencoes) {
        this.historicoDeManutencoes = historicoDeManutencoes;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipamento)) return false;

        Equipamento that = (Equipamento) o;

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
        return nome;
    }
}
