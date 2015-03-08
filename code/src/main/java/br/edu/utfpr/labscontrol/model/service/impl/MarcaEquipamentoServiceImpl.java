package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.MarcaEquipamentoData;
import br.edu.utfpr.labscontrol.model.entity.MarcaEquipamento;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.MarcaEquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by EdsonGustavo on 28/02/2015.
 */
@Service
public class MarcaEquipamentoServiceImpl extends CrudService<MarcaEquipamento, Integer> implements MarcaEquipamentoService {
    @Autowired
    private MarcaEquipamentoData marcaEquipamentoData;

    @Override
    protected JpaRepository<MarcaEquipamento, Integer> getData() {
        return this.marcaEquipamentoData;
    }

    @Override
    public List<MarcaEquipamento> findByNomeContaining(String nome) {
        return this.marcaEquipamentoData.findByNomeContaining(nome);
    }
}
