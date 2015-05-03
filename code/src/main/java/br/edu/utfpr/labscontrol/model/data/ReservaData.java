package br.edu.utfpr.labscontrol.model.data;

import br.edu.utfpr.labscontrol.model.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 29/03/2015.
 */
public interface ReservaData extends JpaRepository<Reserva, Integer> {

    /**
     * Verifica se existe reserva na data e ambiente específicado, e se o horário de inicio e fim está contido
     * em algum horário
     * @param data
     * @param horaInicio
     * @param horaFim
     * @param ambienteId
     * @return
     */
    @Query("select r from Reserva r where r.data = ?1 and " +
            "((?2 > r.horaInicio and ?2 < r.horaFim) or (?3 > r.horaInicio and ?3 < r.horaFim)) or " +
            "((r.horaInicio > ?2 and r.horaInicio < ?3) or (r.horaFim > ?2 and r.horaFim < ?3)) and " +
            "r.ambiente.id = ?4 and r.confirmada = 1")
    List<Reserva> findByDataAndHoraInicioBetweenAndHoraFimBetweenAndAmbienteIdAndConfirmada(Date data, Date horaInicio, Date horaFim, Integer ambienteId);

    List<Reserva> findByDataBetween(Date start, Date end);
}
