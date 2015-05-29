package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface ObservacaoData extends JpaRepository<Observacao, Integer> {
    List<Observacao> findByDescricaoContaining(String descricao);
}
