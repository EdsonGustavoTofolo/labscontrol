package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.CategoriaEquipamentoData;
import br.edu.utfpr.labscontrol.model.data.CategoriaMaterialData;
import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class CategoriaMaterialServiceImpl extends CrudService<CategoriaMaterial, Integer> implements CategoriaMaterialService {
    @Autowired
    private CategoriaMaterialData categoriaMaterialData;

    @Override
    @Transactional(readOnly = true)
    protected JpaRepository<CategoriaMaterial, Integer> getData() {
        return this.categoriaMaterialData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaMaterial> findByNomeContaining(String nome) {
        return this.categoriaMaterialData.findByNomeContaining(nome);
    }
}
