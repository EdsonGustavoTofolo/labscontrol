package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.MarcaMaterial;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaMaterialService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class MarcaMaterialConverter extends BaseConverter<MarcaMaterial, Integer> {
    @Autowired
    private MarcaMaterialService marcaMaterialService;

    @Override
    protected ICrudService<MarcaMaterial, Integer> getService() {
        return marcaMaterialService;
    }

    @Override
    protected String getAsString(MarcaMaterial value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
