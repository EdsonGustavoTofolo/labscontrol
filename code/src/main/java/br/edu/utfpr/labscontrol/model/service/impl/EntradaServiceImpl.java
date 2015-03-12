package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.EntradaData;
import br.edu.utfpr.labscontrol.model.entity.Entrada;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 08/03/2015.
 */
@Service
public class EntradaServiceImpl extends CrudService<Entrada, Integer> implements EntradaService {
    @Autowired
    private EntradaData entradaData;

    @Override
    protected JpaRepository<Entrada, Integer> getData() {
        return entradaData;
    }

    @Override
    public List<Entrada> findByData(Date data) {
        return entradaData.findByData(data);
    }
}
