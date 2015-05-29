package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.CategoriaObservacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface CategoriaObservacaoData extends JpaRepository<CategoriaObservacao, Integer> {
    List<CategoriaObservacao> findByDescricaoContaining(String descricao);
}
