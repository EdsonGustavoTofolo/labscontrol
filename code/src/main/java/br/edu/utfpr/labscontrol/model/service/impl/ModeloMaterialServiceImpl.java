package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ModeloMaterialData;
import br.edu.utfpr.labscontrol.model.entity.ModeloMaterial;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ModeloMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class ModeloMaterialServiceImpl extends CrudService<ModeloMaterial, Integer> implements ModeloMaterialService {
    @Autowired
    private ModeloMaterialData modeloMaterialData;

    @Override
    protected JpaRepository<ModeloMaterial, Integer> getData() {
        return this.modeloMaterialData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModeloMaterial> findByNomeContaining(String nome) {
        return this.modeloMaterialData.findByNomeContaining(nome);
    }
}
