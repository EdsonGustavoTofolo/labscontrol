package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MaterialService extends ICrudService<Material, Integer> {
    /**
     * Método responsável por retornar uma lista de materiais
     * onde o nome contenha o valor passado pelo parâmetro 'nome'
     * @param nome
     * @return
     */
    List<Material> findByNomeContaining(String nome);

    /**
     * Obtém uma lista de materiais vinculados ao equipamento passado por parâmetro
     * @param equipamento
     * @return
     */
    List<Material> findByEquipamento(Equipamento equipamento);

    /**
     * Obtém uma lista de materiais vinculados ao material de consumo passado por parâmetro
     * @param materialDeConsumo
     * @return
     */
    List<Material> findByMaterialDeConsumo(MaterialDeConsumo materialDeConsumo);

    /**
     * Obtém uma lista de materiais vinculados ao fornecedor passado por parâmetro
     * @param fornecedor
     * @return
     */
    List<Material> findByFornecedor(Fornecedor fornecedor);
}
