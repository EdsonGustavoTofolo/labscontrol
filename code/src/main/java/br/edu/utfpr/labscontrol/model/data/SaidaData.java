package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Saida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 01/03/2015.
 */
public interface SaidaData extends JpaRepository<Saida, Integer> {
    List<Saida> findByData(Date data);
}
