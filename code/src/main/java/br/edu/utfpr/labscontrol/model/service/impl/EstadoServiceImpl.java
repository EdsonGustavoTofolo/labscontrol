package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.EstadoData;
import br.edu.utfpr.labscontrol.model.entity.Estado;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Service
public class EstadoServiceImpl extends CrudService<Estado, Integer> implements EstadoService {
    @Autowired
    private EstadoData estadoData;
    @Override
    protected JpaRepository<Estado, Integer> getData() {
        return estadoData;
    }

    @Override
    public List<Estado> findByNomeContaining(String nome) {
        return estadoData.findByNomeContaining(nome);
    }
}
