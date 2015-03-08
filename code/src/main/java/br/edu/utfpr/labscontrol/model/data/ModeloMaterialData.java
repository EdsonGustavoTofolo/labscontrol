package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.ModeloMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface ModeloMaterialData extends JpaRepository<ModeloMaterial, Integer> {
    List<ModeloMaterial> findByNomeContaining(String nome);
}
