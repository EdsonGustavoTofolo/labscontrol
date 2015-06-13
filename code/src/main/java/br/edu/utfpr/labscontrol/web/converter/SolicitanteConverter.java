package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Solicitante;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.SolicitanteService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 13/06/2015.
 */
@Component
public class SolicitanteConverter extends BaseConverter<Solicitante, Integer> {
    @Autowired
    private SolicitanteService solicitanteService;

    @Override
    protected ICrudService<Solicitante, Integer> getService() {
        return solicitanteService;
    }

    @Override
    protected String getAsString(Solicitante value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return Integer.valueOf(value);
    }
}
