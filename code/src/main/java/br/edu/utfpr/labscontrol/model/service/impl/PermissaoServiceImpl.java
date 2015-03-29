package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.PermissaoData;
import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by EdsonGustavo on 27/03/2015.
 */
@Service
public class PermissaoServiceImpl extends CrudService<Permissao, Integer> implements PermissaoService {

    @Autowired
    private PermissaoData permissaoData;

    @Override
    protected JpaRepository<Permissao, Integer> getData() {
        return permissaoData;
    }

    @Override
    public Permissao findByPermissao(String permissao) {
        return permissaoData.findByPermissao(permissao);
    }
}
