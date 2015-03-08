package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.MarcaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
public interface MarcaEquipamentoService extends ICrudService<MarcaEquipamento, Integer> {
    /**
     * Método responsável por retornar uma lista de marcas
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<MarcaEquipamento> findByNomeContaining(String nome);
}
