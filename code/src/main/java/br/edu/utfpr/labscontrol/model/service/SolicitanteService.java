package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Solicitante;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by EdsonGustavo on 13/06/2015.
 */
public interface SolicitanteService extends ICrudService<Solicitante, Integer> {
    List<Solicitante> findByIdentificacaoContaining(String identificacao);
}
