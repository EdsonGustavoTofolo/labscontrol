package br.edu.utfpr.labscontrol.model.service.impl;

import br.edu.utfpr.labscontrol.model.data.ReservaData;
import br.edu.utfpr.labscontrol.model.entity.Reserva;
import br.edu.utfpr.labscontrol.model.framework.CrudService;
import br.edu.utfpr.labscontrol.model.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    /**
     * Verifica se existe reserva na data e ambiente específicado, e se o horário de inicio e fim está contido
     * em algum horário
     *
     * @param data
     * @param horaInicio
     * @param horaFim
     * @param ambienteId
     * @return
     */
    @Override
    public List<Reserva> findByDataAndHoraInicioBetweenAndHoraFimBetweenAndAmbienteIdAndConfirmada(Date data, Date horaInicio, Date horaFim, Integer ambienteId) {
        return reservaData.findByDataAndHoraInicioBetweenAndHoraFimBetweenAndAmbienteIdAndConfirmada(data, horaInicio, horaFim, ambienteId);
    }

    @Override
    public List<Reserva> findByDataBetween(Date start, Date end) {
        return reservaData.findByDataBetween(start, end);
    }


}
