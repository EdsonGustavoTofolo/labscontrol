package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface CategoriaData  extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByNomeContaining(String nome);
}
