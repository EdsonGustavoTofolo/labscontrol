package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Pais;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.PaisService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Component
public class PaisConverter extends BaseConverter<Pais, Integer> {
    @Autowired
    private PaisService paisService;

    @Override
    protected ICrudService<Pais, Integer> getService() {
        return paisService;
    }

    @Override
    protected String getAsString(Pais value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return Integer.valueOf(value);
    }
}
