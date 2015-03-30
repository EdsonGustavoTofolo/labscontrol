package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ReservaData;
import br.edu.utfpr.labscontrol.model.entity.Reserva;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by EdsonGustavo on 29/03/2015.
 */
@Service
public class ReservaServiceImpl extends CrudService<Reserva, Integer> implements ReservaService {
    @Autowired
    private ReservaData reservaData;

    @Override
    protected JpaRepository<Reserva, Integer> getData() {
        return reservaData;
    }
}
