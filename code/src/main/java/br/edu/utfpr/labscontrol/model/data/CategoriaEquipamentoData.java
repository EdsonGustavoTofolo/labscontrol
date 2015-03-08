package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.CategoriaEquipamento;
import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface CategoriaEquipamentoData extends JpaRepository<CategoriaEquipamento, Integer> {
    List<CategoriaEquipamento> findByNomeContaining(String nome);
}
