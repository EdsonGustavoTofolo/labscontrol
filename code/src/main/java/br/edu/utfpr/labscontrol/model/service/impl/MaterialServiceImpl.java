package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.MaterialData;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.Fornecedor;
import br.edu.utfpr.labscontrol.model.entity.Material;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class MaterialServiceImpl extends CrudService<Material, Integer> implements MaterialService {
    @Autowired
    private MaterialData materialData;

    @Override
    protected JpaRepository<Material, Integer> getData() {
        return this.materialData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Material> findByNomeContaining(String nome) {
        return this.materialData.findByNomeContaining(nome);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Material> findByEquipamento(Equipamento equipamento) {
        return this.materialData.findByEquipamento(equipamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Material> findByMaterialDeConsumo(MaterialDeConsumo materialDeConsumo) {
        return this.materialData.findByMaterialDeConsumo(materialDeConsumo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Material> findByFornecedor(Fornecedor fornecedor) {
        return this.materialData.findByFornecedor(fornecedor);
    }
}
