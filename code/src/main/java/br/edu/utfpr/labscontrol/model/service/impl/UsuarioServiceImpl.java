package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.UsuarioData;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class UsuarioServiceImpl extends CrudService<Usuario, Integer> implements UsuarioService {
    @Autowired
    private UsuarioData usuarioData;

    @Override
    protected JpaRepository<Usuario, Integer> getData() {
        return this.usuarioData;
    }
}
