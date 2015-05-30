package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.EquipamentoData;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class EquipamentoServiceImpl extends CrudService<Equipamento, Integer> implements EquipamentoService {
    @Autowired
    private EquipamentoData equipamentoData;

    @Override
    protected JpaRepository<Equipamento, Integer> getData() {
        return this.equipamentoData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipamento> findByNomeContaining(String nome) {
        return this.equipamentoData.findByNomeContaining(nome);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipamento> findByPartNumberContaining(String partNumber) {
        return this.equipamentoData.findByPartNumberContaining(partNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipamento> findByPatrimonioContaining(String patrimonio) {
        return this.equipamentoData.findByPatrimonioContaining(patrimonio);
    }

    @Override
    public List<Equipamento> findByNomeContainingOrPatrimonioContaining(String nome, String patrimonio) {
        return this.equipamentoData.findByNomeContainingOrPatrimonioContaining(nome, patrimonio);
    }


}
