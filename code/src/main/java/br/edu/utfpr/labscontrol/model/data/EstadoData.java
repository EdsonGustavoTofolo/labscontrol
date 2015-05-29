package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 29/05/2015.
 */
public interface EstadoData extends JpaRepository<Estado, Integer> {
    List<Estado> findByNomeContaining(String nome);
}
