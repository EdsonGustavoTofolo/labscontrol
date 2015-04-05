package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by edson on 23/08/2014.
 */
public interface AmbienteData extends JpaRepository<Ambiente, Integer> {
    List<Ambiente> findByIdentificacaoContaining(String identificacao);

    List<Ambiente> findByDescricaoContaining(String descricao);

}
