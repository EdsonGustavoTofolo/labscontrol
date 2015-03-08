package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.SaidaData;
import br.edu.utfpr.labscontrol.model.entity.Saida;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.SaidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 01/03/2015.
 */
@Service
public class SaidaServiceImpl extends CrudService<Saida, Integer> implements SaidaService {
    @Autowired
    private SaidaData saidaData;

    @Override
    protected JpaRepository<Saida, Integer> getData() {
        return this.saidaData;
    }

    @Override
    public List<Saida> findByData(Date data) {
        return this.saidaData.findByData(data);
    }
}
