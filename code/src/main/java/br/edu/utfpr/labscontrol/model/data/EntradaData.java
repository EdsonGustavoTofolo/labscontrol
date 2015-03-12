package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 08/03/2015.
 */
public interface EntradaData extends JpaRepository<Entrada, Integer> {
        List<Entrada> findByData(Date data);
}
