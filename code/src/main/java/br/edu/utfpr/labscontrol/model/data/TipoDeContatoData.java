package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.TipoDeContato;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface TipoDeContatoData extends JpaRepository<TipoDeContato, Integer> {
    List<TipoDeContato> findByDescricaoContaining(String descricao);

    List<TipoDeContato> findByDescricaoContaining(String descricao, Pageable pageable);
}
