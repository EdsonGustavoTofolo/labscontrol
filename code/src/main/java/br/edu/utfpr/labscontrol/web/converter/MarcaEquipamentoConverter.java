package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.MarcaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaEquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Component
public class MarcaEquipamentoConverter extends BaseConverter<MarcaEquipamento, Integer> {
    @Autowired
    private MarcaEquipamentoService marcaEquipamentoService;

    @Override
    protected ICrudService<MarcaEquipamento, Integer> getService() {
        return this.marcaEquipamentoService;
    }

    @Override
    protected String getAsString(MarcaEquipamento value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
