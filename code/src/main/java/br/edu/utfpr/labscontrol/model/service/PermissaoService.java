package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

/**
 * Created by EdsonGustavo on 27/03/2015.
 */
public interface PermissaoService extends ICrudService<Permissao, Integer> {
    Permissao findByPermissao(String permissao);
}
