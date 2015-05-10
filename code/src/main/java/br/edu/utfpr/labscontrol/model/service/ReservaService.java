package br.edu.utfpr.labscontrol.model.service;

import br.edu.utfpr.labscontrol.model.entity.Reserva;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 29/03/2015.
 */
public interface ReservaService extends ICrudService<Reserva, Integer> {
    /**
     * Verifica se existe reserva na data e ambiente específicado, e se o horário de inicio e fim está contido
     * em algum horário
     * @param data
     * @param horaInicio
     * @param horaFim
     * @param ambienteId
     * @return
     */
    List<Reserva> findByDataAndHoraInicioBetweenAndHoraFimBetweenAndAmbienteIdAndConfirmada(Date data, Date horaInicio, Date horaFim, Integer ambienteId, Boolean confirmada);

    List<Reserva> findByDataBetween(Date start, Date end);
}
