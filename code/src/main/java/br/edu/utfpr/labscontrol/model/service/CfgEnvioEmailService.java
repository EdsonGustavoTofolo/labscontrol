package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.CfgEnvioEmail;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

/**
 * Created by EdsonGustavo on 16/05/2015.
 */
public interface CfgEnvioEmailService extends ICrudService<CfgEnvioEmail, Integer> {
    CfgEnvioEmail findByAtiva(Boolean ativa);
}
