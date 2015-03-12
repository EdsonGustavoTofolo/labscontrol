package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Entrada;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 08/03/2015.
 */
public interface EntradaService extends ICrudService<Entrada, Integer> {
    List<Entrada> findByData(Date data);
}
