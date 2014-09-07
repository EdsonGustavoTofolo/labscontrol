package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.TipoDeContatoData;
import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.TipoDeContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class TipoDeContatoServiceImpl extends CrudService<TipoDeContato, Integer> implements TipoDeContatoService {
    @Autowired
    private TipoDeContatoData tipoDeContatoData;

    @Override
    protected JpaRepository<TipoDeContato, Integer> getData() {
        return this.tipoDeContatoData;
    }

    @Override
    public List<TipoDeContato> findByDescricaoContaining(String descricao) {
        return this.tipoDeContatoData.findByDescricaoContaining(descricao);
    }
}
