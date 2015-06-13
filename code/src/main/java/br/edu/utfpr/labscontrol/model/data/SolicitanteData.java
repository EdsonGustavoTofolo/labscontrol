package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EdsonGustavo on 13/06/2015.
 */
public interface SolicitanteData extends JpaRepository<Solicitante, Integer> {
    List<Solicitante> findByIdentificacaoContaining(String identificacao);
}
