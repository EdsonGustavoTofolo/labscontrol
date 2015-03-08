package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaEquipamentoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Component
public class CategoriaEquipamentoConverter extends BaseConverter<CategoriaEquipamento, Integer> {
    @Autowired
    private CategoriaEquipamentoService categoriaEquipamentoService;

    @Override
    protected ICrudService<CategoriaEquipamento, Integer> getService() {
        return this.categoriaEquipamentoService;
    }

    @Override
    protected String getAsString(CategoriaEquipamento value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
