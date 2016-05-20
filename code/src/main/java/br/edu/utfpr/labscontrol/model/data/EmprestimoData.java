package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
public interface EmprestimoData extends JpaRepository<Emprestimo, Integer> {
    @Query("select e from Emprestimo e where e.solicitante.id = ?1 " +
            "and exists (select i.emprestimo.id from EmprestimoItem i where i.emprestimo.id = e.id " +
            "               and i.baixado = false)")
    List<Emprestimo> findByPendenciasDoSolicitanteId(Integer solicitanteId);

    /*@Query(value = "select sol.id as solicitanteId, sol.identificacao as solicitanteIdent, sol.nome as solicitanteNome," +
           "emp.id,emp.data as emprestimoData,emp.observacao as emprestimoObs," +
           "cast(case when ite.baixado = 1 then 'Sim' else 'Não' end as char(3)) as baixado, ite.dataDevolucao, ite.quantidade, ite.quantidadeBaixada," +
           "case when equip.id is null then mat.id else equip.id end as itemId," +
           "case when equip.nome is null then mat.nome else equip.nome end as itemNome," +
           "equip.patrimonio, equip.partNumber from solicitantes as sol inner join emprestimos as emp on emp.solicitanteId=sol.id " +
           "inner join emprestimos_itens as ite on ite.emprestimoId=emp.id left join equipamentos as equip on equip.id=ite.equipamentoId " +
           "left join materiais_de_consumo as mat on mat.id=ite.materialDeConsumoId " +
           "where emp.data between ?1 and ?2 and sol.id = ?3 and ((?4 = true) and (ite.baixado = 1)) order by sol.id", nativeQuery = true)
    List<Emprestimo> findByDataBetweenAndSolicitanteIdAndItemBaixado(Date dataInicial, Date dataFinal, Integer solicitanteId, Boolean itensBaixados);*/

    /*@Query(value = "select sol.id as solicitanteId, sol.identificacao as solicitanteIdent, sol.nome as solicitanteNome, " +
            "emp.id, emp.data as emprestimoData, emp.observacao as emprestimoObs, " +
            "cast(case when ite.baixado = 1 then 'Sim' else 'Não' end as char(3)) as baixado, ite.dataDevolucao, ite.quantidade, ite.quantidadeBaixada, " +
            "case when equip.id is null then mat.id else equip.id end as itemId, " +
            "case when equip.nome is null then mat.nome else equip.nome end as itemNome, " +
            "equip.patrimonio, equip.partNumber from solicitantes as sol inner join emprestimos as emp on emp.solicitanteId=sol.id " +
            "inner join emprestimos_itens as ite on ite.emprestimoId=emp.id left join equipamentos as equip on equip.id=ite.equipamentoId " +
            "left join materiais_de_consumo as mat on mat.id=ite.materialDeConsumoId " +
            "where sol.id = ?1 order by sol.id", nativeQuery = true)
    List findBySolicitanteId(Integer id);*/

    /*@Query("SELECT e FROM Emprestimo e " +
            "JOIN EmprestimoItem ite ON ite.emprestimo.id=e.id " +
            "JOIN Solicitante s ON s.id=e.solicitante.id " +
            "LEFT JOIN Equipamento eq ON eq.id=ite.equipamento.id " +
            "LEFT JOIN MaterialDeConsumo mat ON mat.id=ite.materialDeConsumo.id " +
            "WHERE e.data BETWEEN ?1 AND ?2 " +
            "AND s.id=?3 " +
            "AND ite.baixado = ?4 ORDER BY s.id")
    List<Emprestimo> findByDataBetweenAndSolicitante_IdAndEmprestimoItens_Baixado(Date dataInicial, Date dataFinal, Integer solicitanteId, Boolean baixado);*/

    @Query("SELECT e FROM Emprestimo e, EmprestimoItem ite " +
            "WHERE e.data BETWEEN ?1 AND ?2 " +
            "AND e.solicitante.id=?3 " +
            "AND ite.baixado = ?4 ORDER BY e.solicitante.id")
    List<Emprestimo> findByDataBetweenAndSolicitante_IdAndEmprestimoItens_Baixado(Date dataInicial, Date dataFinal, Integer solicitanteId, Boolean baixado);
}
