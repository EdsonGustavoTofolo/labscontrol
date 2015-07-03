package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.*;
import br.edu.utfpr.labscontrol.model.service.*;
import br.edu.utfpr.labscontrol.web.util.JsfUtil;
import javafx.scene.paint.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EdsonGustavo on 02/07/2015.
 */
@Controller
@Scope("view")
public class RelatoriosController {
    @Autowired private EquipamentoService equipamentoService;
    @Autowired private MaterialDeConsumoService materialDeConsumoService;
    @Autowired private CategoriaEquipamentoService categoriaEquipamentoService;
    @Autowired private CategoriaMaterialService categoriaMaterialService;
    @Autowired private ReservaService reservaService;
    private Equipamento equipamento;
    private MaterialDeConsumo materialDeConsumo;
    private CategoriaEquipamento categoriaEquipamento;
    private CategoriaMaterial categoriaMaterial;
    private Boolean considerarPeriodo, semDataDeRetorno;
    private Date dIni;
    private Date dFin;

    @PostConstruct
    public void init() {

    }

    public void printMateriaisDeConsumos() {
        Integer id = 0;
        if (this.materialDeConsumo != null) {
            id = this.materialDeConsumo.getId();
        }
        List<MaterialDeConsumo> lista = null;
        if (id > 0 || this.categoriaMaterial != null) {
            lista = this.materialDeConsumoService.findByIdOrCategoria(id, this.categoriaMaterial);
        } else {
            lista = this.materialDeConsumoService.findAll();
        }
        JsfUtil.printAndShowOnBrowser(lista, "MateriaisDeConsumosReport.jasper", new HashMap<>(), "RelMateriaisDeConsumosReport");
    }

    public void printAmbientes() {
        List<Reserva> lista = reservaService.findAll();
        JsfUtil.printAndShowOnBrowser(lista, "AmbientesReport.jasper", new HashMap<>(), "RelAmbientes");
    }

    public void printEquipamentosEmManutencao() {
        List<Equipamento> lista = null;
        if ( (dIni != null) && (dFin != null) && (dIni.before(dFin) || dIni.equals(dFin)) ) {
            lista = this.equipamentoService.getEquipamentoEmManutencao(dIni, dFin, semDataDeRetorno);
        } else {
            lista = this.equipamentoService.findAll();
        }
        JsfUtil.printAndShowOnBrowser(lista, "EquipamentosEmManutencaoReport.jasper", new HashMap<>(), "RelEquipamentosEmManutencao");
    }

    public void printEquipamentos() {
        Integer id = 0;
        if (this.equipamento != null) {
            id = this.equipamento.getId();
        }

        List<Equipamento> lista = null;
        if (id > 0 || this.categoriaEquipamento != null) {
            lista = this.equipamentoService.findByIdOrCategoria(id, categoriaEquipamento);
        } else {
            lista = this.equipamentoService.findAll();
        }
        JsfUtil.printAndShowOnBrowser(lista, "EquipamentosReport.jasper", new HashMap<>(), "RelEquipamentos");
    }

    public void printEquipamentosEmprestados() {
        List<Equipamento> lista = null;
        if (this.considerarPeriodo != null && this.considerarPeriodo) {
            if ( (dIni != null) && (dFin != null) && (dIni.before(dFin) || dIni.equals(dFin)) ) {
                lista = this.equipamentoService.getEquipamentoDataBetweenDataIniAndDataFim(dIni, dFin);
            } else {
                lista = this.equipamentoService.findAll();
            }
        } else {
            lista = this.equipamentoService.findAll();
        }
        JsfUtil.printAndShowOnBrowser(lista, "EquipamentosEmprestados.jasper", new HashMap<>(), "RelEquipamentosEmprestados");
    }

    public void printMateriaisDeConsumoEmFalta() {
        List<MaterialDeConsumo> lista = this.materialDeConsumoService.findByQtdAtualLessThanQtdMin();
        JsfUtil.printAndShowOnBrowser(lista, "MateriaisDeConsumoEmFaltaReport.jasper", new HashMap<>(), "RelMateriaisDeConsumoEmFaltaReport");
    }

    public void printMateriaisDeConsumoEmprestados() {
        List<MaterialDeConsumo> lista = null;
        if (this.considerarPeriodo != null && this.considerarPeriodo) {
            if ( (dIni != null) && (dFin != null) && (dIni.before(dFin) || dIni.equals(dFin)) ) {
                lista = this.materialDeConsumoService.getMaterialDataBetweenDataIniAndDataFim(dIni, dFin);
            } else {
                lista = this.materialDeConsumoService.findAll();
            }
        } else {
            lista = this.materialDeConsumoService.findAll();
        }
        JsfUtil.printAndShowOnBrowser(lista, "MateriaisDeConsumoEmprestadosReport.jasper", new HashMap<>(), "RelMateriaisDeConsumoEmprestados");
    }

    public List<Equipamento> completeEquipamento(String nome) {
        return equipamentoService.findByNomeContaining(nome);
    }

    public List<MaterialDeConsumo> completeMaterialDeConsumo(String nome) {
        return materialDeConsumoService.findByNomeContaining(nome);
    }

    public List<CategoriaEquipamento> completeCategoriaEquipamento(String nome) {
        return categoriaEquipamentoService.findByNomeContaining(nome);
    }

    public List<CategoriaMaterial> completeCategoriaMaterial(String nome) {
        return categoriaMaterialService.findByNomeContaining(nome);
    }

    public CategoriaEquipamento getCategoriaEquipamento() {
        return categoriaEquipamento;
    }

    public void setCategoriaEquipamento(CategoriaEquipamento categoriaEquipamento) {
        this.categoriaEquipamento = categoriaEquipamento;
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

    public CategoriaMaterial getCategoriaMaterial() {
        return categoriaMaterial;
    }

    public void setCategoriaMaterial(CategoriaMaterial categoriaMaterial) {
        this.categoriaMaterial = categoriaMaterial;
    }

    public Date getdFin() {
        return dFin;
    }

    public void setdFin(Date dFin) {
        this.dFin = dFin;
    }

    public Date getdIni() {
        return dIni;
    }

    public void setdIni(Date dIni) {
        this.dIni = dIni;
    }

    public Boolean getConsiderarPeriodo() {
        return considerarPeriodo;
    }

    public void setConsiderarPeriodo(Boolean considerarPeriodo) {
        this.considerarPeriodo = considerarPeriodo;
    }

    public Boolean getSemDataDeRetorno() {
        return semDataDeRetorno;
    }

    public void setSemDataDeRetorno(Boolean semDataDeRetorno) {
        this.semDataDeRetorno = semDataDeRetorno;
    }
}
