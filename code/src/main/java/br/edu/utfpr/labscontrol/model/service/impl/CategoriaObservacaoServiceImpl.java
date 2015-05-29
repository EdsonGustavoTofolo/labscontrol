package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.CategoriaObservacaoData;
import br.edu.utfpr.labscontrol.model.entity.CategoriaObservacao;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.CategoriaObservacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Service
public class CategoriaObservacaoServiceImpl extends CrudService<CategoriaObservacao, Integer> implements CategoriaObservacaoService {
    @Autowired
    private CategoriaObservacaoData categoriaObservacaoData;

    @Override
    public List<CategoriaObservacao> findByDescricaoContaining(String descricao) {
        return categoriaObservacaoData.findByDescricaoContaining(descricao);
    }

    @Override
    protected JpaRepository<CategoriaObservacao, Integer> getData() {
        return categoriaObservacaoData;
    }
}
