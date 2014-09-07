package br.edu.utfpr.labscontrol.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by edson on 10/08/2014.
 */
@Entity
@Table(name = "materiais")
public class Material implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "Campo Nome: Preenchimento Obrigatório!")
    @Length(max = 45, message = "Campo Nome: Não pode ultrapassar {max} caracteres!")
    @Column(name = "nome", length = 45, nullable = false)
    private String nome;
    @Length(max = 60, message = "Campo Descrição: Não pode ultrapassar {max} caracteres!")
    @Column(name = "descricao", length = 60)
    private String descricao;
    @Length(max = 255, message = "Campo Foto: Não pode ultrapassar {max} caracteres!")
    @Column(name = "foto", length = 255)
    private String foto;
    @ManyToOne
    @JoinColumn(name = "materialDeConsumoId", referencedColumnName = "id")
    private MaterialDeConsumo materialDeConsumo;
    @ManyToOne
    @JoinColumn(name = "equipamentoId", referencedColumnName = "id")
    private Equipamento equipamento;
    @NotNull(message = "Campo Categoria: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "categoriaId", referencedColumnName = "id", nullable = false)
    private Categoria categoria;
    @NotNull(message = "Campo Modelo: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "modeloId", referencedColumnName = "id", nullable = false)
    private Modelo modelo;
    @NotNull(message = "Campo Marca: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "marcaId", referencedColumnName = "id", nullable = false)
    private Marca marca;
    @NotNull(message = "Campo Fornecedor: Preenchimento Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "fornecedorId", referencedColumnName = "id", nullable = false)
    private Fornecedor fornecedor;

    public Material() {

    }

    public Material(String nome, String descricao, String foto, MaterialDeConsumo materialDeConsumo, Equipamento equipamento,
                    Categoria categoria, Modelo modelo, Marca marca, Fornecedor fornecedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.materialDeConsumo = materialDeConsumo;
        this.equipamento = equipamento;
        this.categoria = categoria;
        this.modelo = modelo;
        this.marca = marca;
        this.fornecedor = fornecedor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public MaterialDeConsumo getMaterialDeConsumo() {
        return materialDeConsumo;
    }

    public void setMaterialDeConsumo(MaterialDeConsumo materialDeConsumo) {
        this.materialDeConsumo = materialDeConsumo;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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
        if (!(o instanceof Material)) return false;

        Material material = (Material) o;

        if (id != null ? !id.equals(material.id) : material.id != null) return false;

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
        return "Material{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
