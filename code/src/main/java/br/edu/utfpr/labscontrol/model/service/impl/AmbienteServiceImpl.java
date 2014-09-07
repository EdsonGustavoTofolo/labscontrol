package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.AmbienteData;
import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.AmbienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class AmbienteServiceImpl extends CrudService<Ambiente, Integer> implements AmbienteService {
    @Autowired
    private AmbienteData ambienteData;

    @Override
    protected JpaRepository<Ambiente, Integer> getData() {
        return this.ambienteData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ambiente> findByDescricaoContaining(String descricao) {
        return this.ambienteData.findByDescricaoContaining(descricao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ambiente> findByIdentificacaoContaining(String identificacao) {
        return this.ambienteData.findByIdentificacaoContaining(identificacao);
    }
}
