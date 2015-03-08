package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.HistoricoDeManutencaoData;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.HistoricoDeManutencao;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.HistoricoDeManutencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class HistoricoDeManutencaoServiceImpl extends CrudService<HistoricoDeManutencao, Integer> implements HistoricoDeManutencaoService {
    @Autowired
    private HistoricoDeManutencaoData historicoDeManutencaoData;

    @Override
    protected JpaRepository<HistoricoDeManutencao, Integer> getData() {
        return this.historicoDeManutencaoData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoricoDeManutencao> findByEquipamento(Equipamento equipamento) {
        return this.historicoDeManutencaoData.findByEquipamento(equipamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoricoDeManutencao> findByMotivoDoDefeitoContaining(String motivoDoDefeito) {
        return this.historicoDeManutencaoData.findByMotivoDoDefeitoContaining(motivoDoDefeito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoricoDeManutencao> findByManutencaoRealizadaContaining(String manutencaoRealizada) {
        return this.findByManutencaoRealizadaContaining(manutencaoRealizada);
    }

    @Override
    public void deleteById(Integer id) {
        historicoDeManutencaoData.deleteById(id);
    }
}
