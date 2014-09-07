package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MarcaData  extends JpaRepository<Marca, Integer> {
    List<Marca> findByNomeContaining(String nome);
}
