package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.UsuarioData;
import br.edu.utfpr.labscontrol.model.entity.Permissao;
import br.edu.utfpr.labscontrol.model.entity.Usuario;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
@Service
public class UsuarioServiceImpl extends CrudService<Usuario, Integer> implements UsuarioService, UserDetailsService {
    @Autowired
    private UsuarioData usuarioData;

    @Override
    protected JpaRepository<Usuario, Integer> getData() {
        return this.usuarioData;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioData.findByUsername(s);
        if (usuario == null)
            throw new UsernameNotFoundException("Login inválido!");
        return usuario;
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioData.findByUsername(username);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioData.findByEmail(email);
    }
}
