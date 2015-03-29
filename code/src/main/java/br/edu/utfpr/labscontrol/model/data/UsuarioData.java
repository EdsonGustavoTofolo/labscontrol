package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by edson on 23/08/2014.
 */
public interface UsuarioData extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
