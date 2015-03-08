package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
public interface CategoriaMaterialData extends JpaRepository<CategoriaMaterial, Integer> {
    List<CategoriaMaterial> findByNomeContaining(String nome);
}
