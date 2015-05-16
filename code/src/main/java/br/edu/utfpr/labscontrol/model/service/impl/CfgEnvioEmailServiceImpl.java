package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.CfgEnvioEmailData;
import br.edu.utfpr.labscontrol.model.entity.CfgEnvioEmail;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.CfgEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by EdsonGustavo on 16/05/2015.
 */
@Service
public class CfgEnvioEmailServiceImpl extends CrudService<CfgEnvioEmail, Integer> implements CfgEnvioEmailService {
    @Autowired
    private CfgEnvioEmailData cfgEnvioEmailData;

    @Override
    protected JpaRepository<CfgEnvioEmail, Integer> getData() {
        return cfgEnvioEmailData;
    }

    @Override
    public CfgEnvioEmail findByAtiva(Boolean ativa) {
        return this.cfgEnvioEmailData.findByAtiva(ativa);
    }
}
