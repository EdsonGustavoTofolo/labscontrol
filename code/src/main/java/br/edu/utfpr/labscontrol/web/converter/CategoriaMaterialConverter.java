package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaMaterialService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class CategoriaMaterialConverter extends BaseConverter<CategoriaMaterial, Integer> {
    @Autowired
    private CategoriaMaterialService categoriaMaterialService;

    @Override
    protected ICrudService<CategoriaMaterial, Integer> getService() {
        return categoriaMaterialService;
    }

    @Override
    protected String getAsString(CategoriaMaterial value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
