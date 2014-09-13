package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.enumeration.TiposDeContatoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface ContatoData  extends JpaRepository<Contato, Integer> {
    List<Contato> findByFornecedor(Fornecedor fornecedor);

    List<Contato> findByFornecedorAndTipoDeContato(Fornecedor fornecedor, TiposDeContatoEnum tipoDeContato);
}
