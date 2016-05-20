package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Emprestimo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 03/05/2015.
 */
public interface EmprestimoService extends ICrudService<Emprestimo, Integer> {
    List<Emprestimo> findByPendenciasDoSolicitanteId(Integer solicitanteId);
    List<Emprestimo> findByDataBetweenAndSolicitante_IdAndEmprestimoItens_Baixado(Date dataInicial, Date dataFinal, Integer solicitanteId, Boolean baixado);
    List<Emprestimo> findBySolicitanteId(Integer id);
}
