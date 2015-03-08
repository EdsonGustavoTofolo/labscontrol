package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.MarcaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MarcaMaterialData extends JpaRepository<MarcaMaterial, Integer> {
    List<MarcaMaterial> findByNomeContaining(String nome);
}
