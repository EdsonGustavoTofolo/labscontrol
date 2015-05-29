package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.CidadeData;
import br.edu.utfpr.labscontrol.model.entity.Cidade;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Service
public class CidadeServiceImpl extends CrudService<Cidade, Integer> implements CidadeService {
    @Autowired
    private CidadeData cidadeData;

    @Override
    public List<Cidade> findByNomeContaining(String nome) {
        return cidadeData.findByNomeContaining(nome);
    }

    @Override
    protected JpaRepository<Cidade, Integer> getData() {
        return cidadeData;
    }
}
