package br.edu.utfpr.labscontrol.web.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 31/05/2015.
 */
public class HorariosManha extends Horarios {

    public HorariosManha() {
        super();
    }

    @Override
    public List<Date> getSelectedHorarios() {
        List<Date> lista = new ArrayList<>();
        try {
            if (getPrimeiro()) {
                lista.add(getHorario("07:30"));
                lista.add(getHorario("08:20"));
            }
            if (getSegundo()) {
                lista.add(getHorario("08:20"));
                lista.add(getHorario("09:10"));
            }
            if (getTerceiro()) {
                lista.add(getHorario("09:10"));
                lista.add(getHorario("10:00"));
            }
            if (getQuarto()) {
                lista.add(getHorario("10:20"));
                lista.add(getHorario("11:10"));
            }
            if (getQuinto()) {
                lista.add(getHorario("11:10"));
                lista.add(getHorario("12:00"));
            }
            if (getSexto()) {
                lista.add(getHorario("12:00"));
                lista.add(getHorario("12:50"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
