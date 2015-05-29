package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Estado;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EstadoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Component
public class EstadoConverter extends BaseConverter<Estado, Integer> {
    @Autowired
    private EstadoService estadoService;

    @Override
    protected ICrudService<Estado, Integer> getService() {
        return estadoService;
    }

    @Override
    protected String getAsString(Estado value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return Integer.valueOf(value);
    }
}
