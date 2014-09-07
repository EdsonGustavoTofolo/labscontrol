package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface TipoDeContatoService extends ICrudService<TipoDeContato, Integer> {
    /**
     * Método responsável por retornar uma lista dos tipos de contatos
     * onde a descrição contém o valor passado por parâmetro
     * @param descricao
     * @return
     */
    List<TipoDeContato> findByDescricaoContaining(String descricao);

    /**
     * Retorna os 10 primeiros tipos de contatos conforme parâmetro
     * @param descricao
     * @return
     */
    List<TipoDeContato> complete(String descricao);
}
