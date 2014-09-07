package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface MaterialData  extends JpaRepository<Material, Integer> {
    List<Material> findByNomeContaining(String nome);

    List<Material> findByEquipamento(Equipamento equipamento);

    List<Material> findByMaterialDeConsumo(MaterialDeConsumo materialDeConsumo);

    List<Material> findByFornecedor(Fornecedor fornecedor);
}
