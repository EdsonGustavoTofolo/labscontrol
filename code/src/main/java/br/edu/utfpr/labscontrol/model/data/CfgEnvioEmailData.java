package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.CfgEnvioEmail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by EdsonGustavo on 16/05/2015.
 */
public interface CfgEnvioEmailData extends JpaRepository<CfgEnvioEmail, Integer> {
    CfgEnvioEmail findByAtiva(Boolean ativa);
}
