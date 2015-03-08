package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.HistoricoDeManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface HistoricoDeManutencaoData  extends JpaRepository<HistoricoDeManutencao, Integer> {
    List<HistoricoDeManutencao> findByEquipamento(Equipamento equipamento);
    List<HistoricoDeManutencao> findByMotivoDoDefeitoContaining(String motivoDoDefeito);
    List<HistoricoDeManutencao> findByManutencaoRealizadaContaining(String manutencaoRealizada);

    @Transactional
    @Modifying
    @Query("DELETE FROM HistoricoDeManutencao h WHERE h.id = :id")
    void deleteById(@Param("id") Integer id);
}
