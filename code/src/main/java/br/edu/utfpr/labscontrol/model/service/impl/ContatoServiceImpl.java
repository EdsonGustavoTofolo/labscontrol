package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ContatoData;
import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class ContatoServiceImpl extends CrudService<Contato, Integer> implements ContatoService {
    @Autowired
    private ContatoData contatoData;

    @Override
    @Transactional(readOnly = true)
    protected JpaRepository<Contato, Integer> getData() {
        return this.contatoData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contato> findByFornecedor(Fornecedor fornecedor) {
        return this.contatoData.findByFornecedor(fornecedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contato> findByFornecedorAndTipoDeContato(Fornecedor fornecedor, TipoDeContato tipoDeContato) {
        return this.contatoData.findByFornecedorAndTipoDeContato(fornecedor, tipoDeContato);
    }
}
