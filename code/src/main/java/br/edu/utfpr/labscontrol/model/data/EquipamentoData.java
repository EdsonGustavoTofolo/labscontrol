package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface EquipamentoData  extends JpaRepository<Equipamento, Integer> {
    List<Equipamento> findByNomeContaining(String nome);
    List<Equipamento> findByPartNumberContaining(String partNumber);
    List<Equipamento> findByPatrimonioContaining(String patrimonio);
    List<Equipamento> findByNomeContainingOrPatrimonioContaining(String nome, String patrimonio);
    List<Equipamento> findByIdOrCategoria(Integer id, CategoriaEquipamento categoriaEquipamento);
    @Query(value = "SELECT e.* FROM equipamentos e" +
            " WHERE e.id IN (SELECT f3.id FROM emprestimos f1" +
            "                INNER JOIN emprestimos_itens f2" +
            "                   ON f2.emprestimoId = f1.id" +
            "                INNER JOIN equipamentos f3" +
            "                   ON f3.id = f2.equipamentoId" +
            "                WHERE f1.data BETWEEN ?1 AND ?2)", nativeQuery = true)
    List<Equipamento> getEquipamentoDataBetweenDataIniAndDataFim(Date dataIni, Date dataFim);

    @Query(value = "SELECT e.* FROM equipamentos e INNER JOIN historicos_de_manutencoes h on h.equipamentoId = e.id" +
            "        WHERE h.dataDeEnvio between ?1 AND ?2 and ( (?3 = true and h.dataDeRetorno is null) or (?3 = false) )", nativeQuery = true)
    List<Equipamento> getEquipamentoEmManutencao(Date dataIni, Date dataFim, Boolean semRetorno);
}
