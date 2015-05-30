package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface EquipamentoData  extends JpaRepository<Equipamento, Integer> {
    List<Equipamento> findByNomeContaining(String nome);
    List<Equipamento> findByPartNumberContaining(String partNumber);
    List<Equipamento> findByPatrimonioContaining(String patrimonio);
    List<Equipamento> findByNomeContainingOrPatrimonioContaining(String nome, String patrimonio);
}
