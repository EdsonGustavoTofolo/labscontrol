package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.CategoriaObservacao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaObservacaoService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Component
public class CategoriaObservacaoConverter extends BaseConverter<CategoriaObservacao, Integer> {
    @Autowired
    private CategoriaObservacaoService categoriaObservacaoService;

    @Override
    protected ICrudService<CategoriaObservacao, Integer> getService() {
        return categoriaObservacaoService;
    }

    @Override
    protected String getAsString(CategoriaObservacao value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return Integer.valueOf(value);
    }
}
