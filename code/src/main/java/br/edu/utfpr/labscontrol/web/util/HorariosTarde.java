package br.edu.utfpr.labscontrol.web.util;

import EDU.oswego.cs.dl.util.concurrent.FJTask;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 31/05/2015.
 */
public class HorariosTarde extends Horarios {
    public HorariosTarde() {
        super();
    }

    @Override
    public List<Date> getSelectedHorarios() {
        List<Date> lista = new ArrayList<>();
        try {
            if (getPrimeiro()) {
                lista.add(getHorario("13:00"));
                lista.add(getHorario("13:50"));
            }
            if (getSegundo()) {
                lista.add(getHorario("13:50"));
                lista.add(getHorario("14:40"));
            }
            if (getTerceiro()) {
                lista.add(getHorario("14:40"));
                lista.add(getHorario("15:30"));
            }
            if (getQuarto()) {
                lista.add(getHorario("15:50"));
                lista.add(getHorario("16:40"));
            }
            if (getQuinto()) {
                lista.add(getHorario("16:40"));
                lista.add(getHorario("17:30"));
            }
            if (getSexto()) {
                lista.add(getHorario("17:30"));
                lista.add(getHorario("18:20"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
