package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Saida;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 01/03/2015.
 */

public interface SaidaService extends ICrudService<Saida, Integer> {
    List<Saida> findByData(Date data);
}
