package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Estado;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface EstadoService extends ICrudService<Estado, Integer> {
    List<Estado> findByNomeContaining(String nome);
}
