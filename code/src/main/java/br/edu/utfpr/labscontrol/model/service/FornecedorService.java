package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface FornecedorService extends ICrudService<Fornecedor, Integer> {
    /**
     * Método responsável por retornar uma lista de fornecedores
     * onde a razão social contenha o valor passado pelo parâmetro
     * @param razaoSocial
     * @return
     */
    List<Fornecedor> findByRazaoSocialContaining(String razaoSocial);

    /**
     * Método responsável por retornar uma lista de fornecedores
     * onde o nome fantasia contenha o valor passado pelo parâmetro
     * @param nomeFantasia
     * @return
     */
    List<Fornecedor> findByNomeFantasiaContaining(String nomeFantasia);
}
