package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Marca;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MarcaService extends ICrudService<Marca, Integer> {
    /**
     * Método responsável por retornar uma lista de marcas
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<Marca> findByNomeContaining(String nome);
}
