package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Categoria;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface CategoriaService extends ICrudService<Categoria, Integer> {
    /**
     * Método responsável por retornar uma lista de categorias
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<Categoria> findByNomeContaining(String nome);
}
