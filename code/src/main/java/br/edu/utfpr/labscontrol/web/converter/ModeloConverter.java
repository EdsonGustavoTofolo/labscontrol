package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Modelo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class ModeloConverter extends BaseConverter<Modelo, Integer> {
    @Autowired
    private ModeloService modeloService;

    @Override
    protected ICrudService<Modelo, Integer> getService() {
        return modeloService;
    }

    @Override
    protected String getAsString(Modelo value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
