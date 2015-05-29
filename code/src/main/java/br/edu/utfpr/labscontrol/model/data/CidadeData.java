package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface CidadeData extends JpaRepository<Cidade, Integer> {
    List<Cidade> findByNomeContaining(String nome);
}
