package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.CategoriaData;
import br.edu.utfpr.labscontrol.model.entity.Categoria;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class CategoriaServiceImpl extends CrudService<Categoria, Integer> implements CategoriaService {
    @Autowired
    private CategoriaData categoriaData;

    @Override
    @Transactional(readOnly = true)
    protected JpaRepository<Categoria, Integer> getData() {
        return this.categoriaData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findByNomeContaining(String nome) {
        return this.categoriaData.findByNomeContaining(nome);
    }
}
