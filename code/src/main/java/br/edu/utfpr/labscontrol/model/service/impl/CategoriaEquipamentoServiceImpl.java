package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.CategoriaEquipamentoData;
import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaEquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Service
public class CategoriaEquipamentoServiceImpl extends CrudService<CategoriaEquipamento, Integer> implements CategoriaEquipamentoService {
    @Autowired
    private CategoriaEquipamentoData categoriaEquipamentoData;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaEquipamento> findByNomeContaining(String nome) {
        return this.categoriaEquipamentoData.findByNomeContaining(nome);
    }

    @Override
    @Transactional(readOnly = true)
    protected JpaRepository<CategoriaEquipamento, Integer> getData() {
        return this.categoriaEquipamentoData;
    }
}
