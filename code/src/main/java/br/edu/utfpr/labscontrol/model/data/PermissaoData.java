package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by EdsonGustavo on 22/03/2015.
 */
public interface PermissaoData extends JpaRepository<Permissao, Integer> {
    Permissao findByPermissao(String permissao);
}
