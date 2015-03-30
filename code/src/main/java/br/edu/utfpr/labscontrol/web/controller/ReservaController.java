package br.edu.utfpr.labscontrol.web.controller;

import br.edu.utfpr.labscontrol.model.entity.Reserva;
import br.edu.utfpr.labscontrol.model.entity.ReservaItem;
import br.edu.utfpr.labscontrol.model.framework.ICrudService;
import br.edu.utfpr.labscontrol.model.service.ReservaItemService;
import br.edu.utfpr.labscontrol.model.service.ReservaService;
import br.edu.utfpr.labscontrol.web.framework.CrudController;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by EdsonGustavo on 29/03/2015.
 */
@Controller
@Scope("view")
public class ReservaController extends CrudController<Reserva, Integer> {
    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ReservaItemService reservaItemService;

    private ScheduleModel scheduleModel;
    private ScheduleEvent scheduleEvent = new DefaultScheduleEvent();

    @Override
    protected void inicializar() {
        scheduleModel = new DefaultScheduleModel();
        populaSchedule();
    }

    private void populaSchedule() {
        for (Reserva reserva : reservaService.findAll()) {
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(reserva.getData());
            inicio.set(inicio.get(Calendar.YEAR), inicio.get(Calendar.MONTH), inicio.get(Calendar.DATE), reserva.getHoraInicio().getHour(), reserva.getHoraInicio().getMinute());
            Calendar fim = Calendar.getInstance();
            fim.setTime(reserva.getData());
            fim.set(fim.get(Calendar.YEAR), fim.get(Calendar.MONTH), fim.get(Calendar.DATE), reserva.getHoraFim().getHour(), reserva.getHoraFim().getMinute());
            scheduleModel.addEvent(new DefaultScheduleEvent(reserva.getAmbiente().getIdentificacao(), inicio.getTime(), fim.getTime()));
        }
    }

    @Override
    protected ICrudService<Reserva, Integer> getService() {
        return reservaService;
    }

    @Override
    protected String getUrlFormPage() {
        return "/pages/reserva/reservaForm.xhtml?faces-redirect=true";
    }
}
