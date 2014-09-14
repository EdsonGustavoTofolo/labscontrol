package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Categoria;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 14/09/2014.
 */
@Component
public class CategoriaConverter extends BaseConverter<Categoria, Integer> {
    @Autowired
    private CategoriaService categoriaService;

    @Override
    protected ICrudService<Categoria, Integer> getService() {
        return categoriaService;
    }

    @Override
    protected String getAsString(Categoria value) {
        return value.getId().toString();
    }

    @Override
    protected Integer getAsObject(String value) {
        return new Integer(value);
    }
}
