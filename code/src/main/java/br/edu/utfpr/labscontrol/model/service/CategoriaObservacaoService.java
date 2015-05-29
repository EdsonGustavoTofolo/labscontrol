package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.CategoriaObservacao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface CategoriaObservacaoService extends ICrudService<CategoriaObservacao, Integer> {
    List<CategoriaObservacao> findByDescricaoContaining(String descricao);
}
