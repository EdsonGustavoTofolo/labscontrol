package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.ModeloEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloEquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Component
public class ModeloEquipamentoConverter extends BaseConverter<ModeloEquipamento, Integer> {
    @Autowired
    private ModeloEquipamentoService modeloEquipamentoService;

    @Override
    protected ICrudService<ModeloEquipamento, Integer> getService() {
        return this.modeloEquipamentoService;
    }

    @Override
    protected String getAsString(ModeloEquipamento value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
