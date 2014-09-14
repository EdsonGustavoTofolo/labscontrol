package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Marca;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class MarcaConverter extends BaseConverter<Marca, Integer> {
    @Autowired
    private MarcaService marcaService;

    @Override
    protected ICrudService<Marca, Integer> getService() {
        return marcaService;
    }

    @Override
    protected String getAsString(Marca value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
