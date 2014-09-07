package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface AmbienteService extends ICrudService<Ambiente, Integer> {
    /**
     * Método responsável por retornar uma lista de ambientes
     * onde a descrição contenha o valor passado pelo parâmetro 'descricao'
     * @param descricao
     * @return lista de ambientes
     */
    List<Ambiente> findByDescricaoContaining(String descricao);

    /**
     * Método responsável por retornar uma lista de ambientes
     * onde a identificação contenha o valor passado pelo parâmetro 'identificacao'
     * @param identificacao
     * @return
     */
    List<Ambiente> findByIdentificacaoContaining(String identificacao);
}
