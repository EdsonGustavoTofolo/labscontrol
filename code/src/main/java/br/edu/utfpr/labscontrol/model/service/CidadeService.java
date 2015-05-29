package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Cidade;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface CidadeService extends ICrudService<Cidade, Integer> {
    List<Cidade> findByNomeContaining(String nome);
}
