package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ObservacaoData;
import br.edu.utfpr.labscontrol.model.entity.Observacao;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ObservacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Service
public class ObservacaoServiceImpl extends CrudService<Observacao, Integer> implements ObservacaoService {
    @Autowired
    private ObservacaoData observacaoData;

    @Override
    protected JpaRepository<Observacao, Integer> getData() {
        return observacaoData;
    }

    @Override
    public List<Observacao> findByDescricaoContaining(String descricao) {
        return observacaoData.findByDescricaoContaining(descricao);
    }
}
