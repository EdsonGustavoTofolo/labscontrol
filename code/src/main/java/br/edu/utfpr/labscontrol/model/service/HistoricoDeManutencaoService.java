package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.HistoricoDeManutencao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface HistoricoDeManutencaoService extends ICrudService<HistoricoDeManutencao, Integer> {
    /**
     * Método responsável por retornar uma lista de históricos das manutenções
     * vinculados ao equipamento passado por parâmetro
     * @param equipamento
     * @return
     */
    List<HistoricoDeManutencao> findByEquipamento(Equipamento equipamento);
    /**
     * Método responsável por retornar uma lista de históricos das manutenções
     * onde o motivo do defeito contenha o valor passado pelo parâmetro 'motivoDoDefeito'
     * @param motivoDoDefeito
     * @return
     */
    List<HistoricoDeManutencao> findByMotivoDoDefeitoContaining(String motivoDoDefeito);

    /**
     * Método responsável por retornar uma lista de históricos das manutenções
     * onde a manutenção realizada contenha o valor passado pelo parâmetro 'manutencaoRealizada'
     * @param manutencaoRealizada
     * @return
     */
    List<HistoricoDeManutencao> findByManutencaoRealizadaContaining(String manutencaoRealizada);

    //TODO os find por data de envio, retorno e defeito
}
