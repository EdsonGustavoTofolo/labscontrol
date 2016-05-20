package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.Date;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface EquipamentoService extends ICrudService<Equipamento, Integer> {
    /**
     * Método responsável por retornar uma lista de equipamentos
     * onde o nome contenha o valor passado pelo parâmetro
     * @param nome
     * @return
     */
    List<Equipamento> findByNomeContaining(String nome);

    /**
     * Método responsável por retornar uma lista de equipamentos
     * onde o part number contenha o valor passado pelo parâmetro
     * @param partNumber
     * @return
     */
    List<Equipamento> findByPartNumberContaining(String partNumber);

    /**
     * Método responsável por retornar uma lista de equipamentos
     * onde o código do partrimônio contenha o valor passado pelo parâmetro
     * @param patrimonio
     * @return
     */
    List<Equipamento> findByPatrimonioContaining(String patrimonio);

    List<Equipamento> findByNomeContainingOrPatrimonioContaining(String nome, String patrimonio);

    List<Equipamento> findByIdOrCategoria(Integer id, CategoriaEquipamento categoriaEquipamento);

    List<Equipamento> getEquipamentoDataBetweenDataIniAndDataFim(Date dataIni, Date dataFim);

    List<Equipamento> getEquipamentoEmManutencao(Date dataIni, Date dataFim, Boolean semRetorno);

    Equipamento findByPatrimonioAndMarca_IdAndModelo_IdAndCategoria_Id(String patrimonio, Integer marcaId, Integer modeloId, Integer categoriaId);
}
