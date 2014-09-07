package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

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
     * onde o código de barras contenha o valor passado pelo parâmetro
     * @param codigoDeBarras
     * @return
     */
    List<Equipamento> findByCodigoDeBarrasContaining(String codigoDeBarras);

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
}
