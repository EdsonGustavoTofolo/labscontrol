package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface FornecedorData  extends JpaRepository<Fornecedor, Integer> {
    List<Fornecedor> findByRazaoSocialContaining(String razaoSocial);

    List<Fornecedor> findByNomeFantasiaContaining(String nomeFantasia);
}
