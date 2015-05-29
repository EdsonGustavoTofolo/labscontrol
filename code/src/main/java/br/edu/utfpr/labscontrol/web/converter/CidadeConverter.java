package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Cidade;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CidadeService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Component
public class CidadeConverter extends BaseConverter<Cidade, Integer> {
    @Autowired
    private CidadeService cidadeService;

    @Override
    protected ICrudService<Cidade, Integer> getService() {
        return cidadeService;
    }

    @Override
    protected String getAsString(Cidade value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return Integer.valueOf(value);
    }
}
