package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.MaterialDeConsumoData;
import br.edu.utfpr.labscontrol.model.entity.CategoriaMaterial;
import br.edu.utfpr.labscontrol.model.entity.Equipamento;
import br.edu.utfpr.labscontrol.model.entity.MaterialDeConsumo;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.MaterialDeConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class MaterialDeConsumoServiceImpl extends CrudService<MaterialDeConsumo, Integer> implements MaterialDeConsumoService {
    @Autowired
    private MaterialDeConsumoData materialDeConsumoData;

    @Override
    protected JpaRepository<MaterialDeConsumo, Integer> getData() {
        return this.materialDeConsumoData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDeConsumo> findByNomeContaining(String nome) {
        return this.materialDeConsumoData.findByNomeContaining(nome);
    }

    @Override
    public List<MaterialDeConsumo> findByIdOrCategoria(Integer id, CategoriaMaterial categoria) {
        return this.materialDeConsumoData.findByIdOrCategoria(id, categoria);
    }

    @Override
    public List<MaterialDeConsumo> getMaterialDataBetweenDataIniAndDataFim(Date dataIni, Date dataFim) {
        return this.materialDeConsumoData.getMaterialDataBetweenDataIniAndDataFim(dataIni, dataFim);
    }

    @Override
    public List<MaterialDeConsumo> findByQtdAtualLessThanQtdMin() {
        return materialDeConsumoData.findByQtdAtualLessThanQtdMin();
    }
}
