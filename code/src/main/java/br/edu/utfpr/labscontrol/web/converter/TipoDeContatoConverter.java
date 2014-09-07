package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.TipoDeContatoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 07/09/2014.
 */
@Component
public class TipoDeContatoConverter extends BaseConverter<TipoDeContato, Integer> {
    @Autowired
    private TipoDeContatoService tipoDeContatoService;

    @Override
    protected ICrudService<TipoDeContato, Integer> getService() {
        return this.tipoDeContatoService;
    }

    @Override
    protected String getAsString(TipoDeContato value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
