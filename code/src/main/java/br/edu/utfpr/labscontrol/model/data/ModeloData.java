package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface ModeloData  extends JpaRepository<Modelo, Integer> {
    List<Modelo> findByNomeContaining(String nome);
}
