package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class FornecedorConverter extends BaseConverter<Fornecedor, Integer> {
    @Autowired
    private FornecedorService fornecedorService;

    @Override
    protected ICrudService<Fornecedor, Integer> getService() {
        return fornecedorService;
    }

    @Override
    protected String getAsString(Fornecedor value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
