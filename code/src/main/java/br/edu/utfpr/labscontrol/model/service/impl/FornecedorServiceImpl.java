package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.FornecedorData;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class FornecedorServiceImpl extends CrudService<Fornecedor, Integer> implements FornecedorService {
    @Autowired
    private FornecedorData fornecedorData;

    @Override
    protected JpaRepository<Fornecedor, Integer> getData() {
        return this.fornecedorData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fornecedor> findByRazaoSocialContaining(String razaoSocial) {
        return this.fornecedorData.findByRazaoSocialContaining(razaoSocial);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Fornecedor> findByNomeFantasiaContaining(String nomeFantasia) {
        return this.fornecedorData.findByNomeFantasiaContaining(nomeFantasia);
    }
}
