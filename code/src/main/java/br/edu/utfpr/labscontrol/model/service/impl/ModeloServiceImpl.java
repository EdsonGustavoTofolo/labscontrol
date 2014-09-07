package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ModeloData;
import br.edu.utfpr.labscontrol.model.entity.Modelo;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class ModeloServiceImpl extends CrudService<Modelo, Integer> implements ModeloService {
    @Autowired
    private ModeloData modeloData;

    @Override
    protected JpaRepository<Modelo, Integer> getData() {
        return this.modeloData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Modelo> findByNomeContaining(String nome) {
        return this.modeloData.findByNomeContaining(nome);
    }
}
