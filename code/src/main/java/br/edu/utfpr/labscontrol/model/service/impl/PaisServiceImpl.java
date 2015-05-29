package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.PaisData;
import br.edu.utfpr.labscontrol.model.entity.Pais;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
@Service
public class PaisServiceImpl extends CrudService<Pais, Integer> implements PaisService {
    @Autowired
    private PaisData paisData;

    @Override
    protected JpaRepository<Pais, Integer> getData() {
        return paisData;
    }

    @Override
    public List<Pais> findByNomeContaining(String nome) {
        return paisData.findByNomeContaining(nome);
    }
}
