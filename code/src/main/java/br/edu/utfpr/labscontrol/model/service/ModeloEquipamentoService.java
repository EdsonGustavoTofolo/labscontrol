package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.ModeloEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
public interface ModeloEquipamentoService extends ICrudService<ModeloEquipamento, Integer> {
    /**
     * Método responsável por retornar uma lista de modelos
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<ModeloEquipamento> findByNomeContaining(String nome);
}
