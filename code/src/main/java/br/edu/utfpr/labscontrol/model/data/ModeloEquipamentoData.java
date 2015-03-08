package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.ModeloEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
public interface ModeloEquipamentoData extends JpaRepository<ModeloEquipamento, Integer> {
    List<ModeloEquipamento> findByNomeContaining(String nome);
}
