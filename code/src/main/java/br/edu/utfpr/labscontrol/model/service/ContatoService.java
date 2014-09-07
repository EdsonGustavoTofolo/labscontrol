package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface ContatoService extends ICrudService<Contato, Integer> {
    /**
     * Obtém todos os contatos vinculados ao fornecedor passado por parâmetro
     * @param fornecedor
     * @return
     */
    List<Contato> findByFornecedor(Fornecedor fornecedor);

    /**
     * Obtém todos os contatos vinculados ao fornecedor e tipo de contato passados por parâmetros
     * @param tipoDeContato
     * @param fornecedor
     * @return
     */
    List<Contato> findByFornecedorAndTipoDeContato(Fornecedor fornecedor, TipoDeContato tipoDeContato);
}
