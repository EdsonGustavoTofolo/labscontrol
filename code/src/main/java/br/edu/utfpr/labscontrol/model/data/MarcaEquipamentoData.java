package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.MarcaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
public interface MarcaEquipamentoData extends JpaRepository<MarcaEquipamento, Integer> {
    List<MarcaEquipamento> findByNomeContaining(String nome);
}
