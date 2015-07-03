package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import javafx.scene.paint.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MaterialDeConsumoData  extends JpaRepository<MaterialDeConsumo, Integer> {
    List<MaterialDeConsumo> findByNomeContaining(String nome);
    List<MaterialDeConsumo> findByIdOrCategoria(Integer id, CategoriaMaterial categoria);
    @Query(value = "SELECT e.* FROM materiais_de_consumo e" +
            " WHERE e.id IN (SELECT f3.id FROM emprestimos f1" +
            "                INNER JOIN emprestimos_itens f2" +
            "                   ON f2.emprestimoId = f1.id" +
            "                INNER JOIN materiais_de_consumo f3" +
            "                   ON f3.id = f2.materialDeConsumoId" +
            "                WHERE f1.data BETWEEN ?1 AND ?2)", nativeQuery = true)
    List<MaterialDeConsumo> getMaterialDataBetweenDataIniAndDataFim(Date dataIni, Date dataFim);

    @Query("SELECT m FROM MaterialDeConsumo m WHERE m.qtdAtual < m.qtdMin")
    List<MaterialDeConsumo> findByQtdAtualLessThanQtdMin();
}
