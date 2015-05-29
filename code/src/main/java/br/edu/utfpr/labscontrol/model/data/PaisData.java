package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface PaisData extends JpaRepository<Pais, Integer> {
    List<Pais> findByNomeContaining(String nome);
}
