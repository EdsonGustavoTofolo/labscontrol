package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by edson on 24/08/2014.
 */
@Component
public class EquipamentoConverter extends BaseConverter<Equipamento, Integer> {
    @Autowired
    private EquipamentoService equipamentoService;

    @Override
    protected ICrudService<Equipamento, Integer> getService() {
        return this.equipamentoService;
    }

    @Override
    protected String getAsString(Equipamento value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
