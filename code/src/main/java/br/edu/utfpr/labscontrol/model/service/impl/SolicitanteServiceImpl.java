package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.SolicitanteData;
import br.edu.utfpr.labscontrol.model.entity.Solicitante;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.AmbienteService;
import br.edu.utfpr.labscontrol.model.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 13/06/2015.
 */
@Service
public class SolicitanteServiceImpl extends CrudService<Solicitante, Integer> implements SolicitanteService {
    @Autowired
    private SolicitanteData solicitanteData;

    @Override
    protected JpaRepository<Solicitante, Integer> getData() {
        return solicitanteData;
    }

    @Override
    public List<Solicitante> findByIdentificacaoContaining(String identificacao) {
        return solicitanteData.findByIdentificacaoContaining(identificacao);
    }

    @Override
    public List<Solicitante> findByNomeContainingOrIdentificacaoContaining(String nome, String identificacao) {
        return solicitanteData.findByNomeContainingOrIdentificacaoContaining(nome, identificacao);
    }
}
