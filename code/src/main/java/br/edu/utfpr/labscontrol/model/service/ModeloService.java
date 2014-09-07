package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Modelo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface ModeloService extends ICrudService<Modelo, Integer> {
    /**
     * Método responsável por retornar uma lista de modelos
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<Modelo> findByNomeContaining(String nome);
}
