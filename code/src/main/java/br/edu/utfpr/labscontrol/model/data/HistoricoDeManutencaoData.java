package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.HistoricoDeManutencao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface HistoricoDeManutencaoData  extends JpaRepository<HistoricoDeManutencao, Integer> {
    List<HistoricoDeManutencao> findByEquipamento(Equipamento equipamento);
    List<HistoricoDeManutencao> findByMotivoDoDefeitoContaining(String motivoDoDefeito);
    List<HistoricoDeManutencao> findByManutencaoRealizadaContaining(String manutencaoRealizada);
}
