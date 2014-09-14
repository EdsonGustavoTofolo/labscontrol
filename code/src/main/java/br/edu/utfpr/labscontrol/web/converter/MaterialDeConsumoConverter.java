package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class MaterialDeConsumoConverter extends BaseConverter<MaterialDeConsumo, Integer> {
    @Autowired
    private MaterialDeConsumoService materialDeConsumoService;

    @Override
    protected ICrudService<MaterialDeConsumo, Integer> getService() {
        return materialDeConsumoService;
    }

    @Override
    protected String getAsString(MaterialDeConsumo value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
