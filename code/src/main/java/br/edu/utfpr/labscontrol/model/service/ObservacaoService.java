package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Observacao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface ObservacaoService extends ICrudService<Observacao, Integer> {
    List<Observacao> findByDescricaoContaining(String descricao);
}
