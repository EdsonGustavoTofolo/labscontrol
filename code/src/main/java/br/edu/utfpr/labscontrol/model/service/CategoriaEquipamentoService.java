package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
public interface CategoriaEquipamentoService extends ICrudService<CategoriaEquipamento, Integer> {
    /**
     * Método responsável por retornar uma lista de categorias
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<CategoriaEquipamento> findByNomeContaining(String nome);
}
