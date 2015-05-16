package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

/**
 * Created by edson on 23/08/2014.
 */
public interface UsuarioService extends ICrudService<Usuario, Integer> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}
