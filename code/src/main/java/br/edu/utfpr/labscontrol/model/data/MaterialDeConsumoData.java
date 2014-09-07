package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MaterialDeConsumoData  extends JpaRepository<MaterialDeConsumo, Integer> {
    List<MaterialDeConsumo> findByNomeContaining(String nome);
}
