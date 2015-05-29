package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Pais;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface PaisService extends ICrudService<Pais, Integer> {
    List<Pais> findByNomeContaining(String nome);
}
