package br.edu.utfpr.labscontrol.web.converter;

import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.AmbienteService;
import br.edu.utfpr.labscontrol.web.framework.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by EdsonGustavo on 30/03/2015.
 */
@Component
public class AmbienteConverter extends BaseConverter<Ambiente, Integer> {
    @Autowired
    private AmbienteService ambienteService;
    /**
     * Metodo responsavel por obter o servico onde sera obtido o objeto selecionado pelo usuario
     * chamando o metodo findById desta interface
     *
     * @return
     */
    @Override
    protected ICrudService<Ambiente, Integer> getService() {
        return ambienteService;
    }

    /**
     * Metodo responsavel por retornar a chave do objeto passado por parametro
     *
     * @param value
     * @return
     */
    @Override
    protected String getAsString(Ambiente value) {
        return value.getId().toString();
    }

    /**
     * Metodo responsavel por retornar o objeto conforme a chave do mesmo passada por parametro
     *
     * @param value
     * @return
     */
    @Override
    protected Integer getAsObject(String value) {
        return Integer.valueOf(value);
    }
}
