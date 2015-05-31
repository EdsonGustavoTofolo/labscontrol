package br.edu.utfpr.labscontrol.web.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 31/05/2015.
 */
public class HorariosNoite extends Horarios {
    public HorariosNoite() {
        super();
    }

    @Override
    public List<Date> getSelectedHorarios() {
        List<Date> lista = new ArrayList<>();
        try {
            if (getPrimeiro()) {
                lista.add(getHorario("18:40"));
                lista.add(getHorario("19:30"));
            }
            if (getSegundo()) {
                lista.add(getHorario("19:30"));
                lista.add(getHorario("20:20"));
            }
            if (getTerceiro()) {
                lista.add(getHorario("20:20"));
                lista.add(getHorario("21:10"));
            }
            if (getQuarto()) {
                lista.add(getHorario("21:20"));
                lista.add(getHorario("22:10"));
            }
            if (getQuinto()) {
                lista.add(getHorario("22:10"));
                lista.add(getHorario("23:00"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
