package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.Date;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MaterialDeConsumoService extends ICrudService<MaterialDeConsumo, Integer> {
    /**
     * Método responsável por retornar uma lista de materiais de consumo
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<MaterialDeConsumo> findByNomeContaining(String nome);
    List<MaterialDeConsumo> findByIdOrCategoria(Integer id, CategoriaMaterial categoria);
    List<MaterialDeConsumo> getMaterialDataBetweenDataIniAndDataFim(Date dataIni, Date dataFim);
    List<MaterialDeConsumo> findByQtdAtualLessThanQtdMin();
}
