package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ModeloEquipamentoData;
import br.edu.utfpr.labscontrol.model.entity.ModeloEquipamento;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloEquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Service
public class ModeloEquipamentoServiceImpl extends CrudService<ModeloEquipamento, Integer> implements ModeloEquipamentoService {
    @Autowired
    private ModeloEquipamentoData modeloEquipamentoData;

    @Override
    protected JpaRepository<ModeloEquipamento, Integer> getData() {
        return this.modeloEquipamentoData;
    }

    @Override
    public List<ModeloEquipamento> findByNomeContaining(String nome) {
        return this.modeloEquipamentoData.findByNomeContaining(nome);
    }
}
