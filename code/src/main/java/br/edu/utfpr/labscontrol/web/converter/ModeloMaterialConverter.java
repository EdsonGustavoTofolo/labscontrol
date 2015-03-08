package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.ModeloMaterial;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloMaterialService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class ModeloMaterialConverter extends BaseConverter<ModeloMaterial, Integer> {
    @Autowired
    private ModeloMaterialService modeloMaterialService;

    @Override
    protected ICrudService<ModeloMaterial, Integer> getService() {
        return modeloMaterialService;
    }

    @Override
    protected String getAsString(ModeloMaterial value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
