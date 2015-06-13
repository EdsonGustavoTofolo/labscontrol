package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
public interface EmprestimoData extends JpaRepository<Emprestimo, Integer> {
    @Query("select e from Emprestimo e where e.solicitante.id = ?1 " +
            "and exists (select i.emprestimo.id from EmprestimoItem i  where i.emprestimo.id = e.id " +
            "               and ( ((i.materialDeConsumo.id > 0) and (i.quantidadeBaixada < i.quantidade)) or " +
            "                     ((i.equipamento.id > 0) and (i.baixado = false)) ))")
    List<Emprestimo> findByPendenciasDoSolicitanteId(Integer solicitanteId);
}
