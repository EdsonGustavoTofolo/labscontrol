package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Contato;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.enumeration.TiposDeContatoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface ContatoData  extends JpaRepository<Contato, Integer> {
    List<Contato> findByFornecedor(Fornecedor fornecedor);

    List<Contato> findByFornecedorAndTipoDeContato(Fornecedor fornecedor, TiposDeContatoEnum tipoDeContato);

    @Transactional
    @Modifying
    @Query("DELETE FROM Contato c WHERE c.id = :id")
    void deleteById(@Param("id") Integer id);
}
