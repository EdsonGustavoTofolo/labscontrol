package br.edu.utfpr.labscontrol.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 31/05/2015.
 */
public abstract class Horarios {
    private Boolean primeiro;
    private Boolean segundo;
    private Boolean terceiro;
    private Boolean quarto;
    private Boolean quinto;
    private Boolean sexto;

    protected Horarios() {
        this.primeiro = Boolean.FALSE;
        this.segundo = Boolean.FALSE;
        this.terceiro = Boolean.FALSE;
        this.quarto = Boolean.FALSE;
        this.quinto = Boolean.FALSE;
        this.sexto = Boolean.FALSE;
    }

    public abstract List<Date> getSelectedHorarios();

    public Boolean getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(Boolean primeiro) {
        this.primeiro = primeiro;
    }

    public Boolean getSegundo() {
        return segundo;
    }

    public void setSegundo(Boolean segundo) {
        this.segundo = segundo;
    }

    public Boolean getTerceiro() {
        return terceiro;
    }

    public void setTerceiro(Boolean terceiro) {
        this.terceiro = terceiro;
    }

    public Boolean getQuarto() {
        return quarto;
    }

    public void setQuarto(Boolean quarto) {
        this.quarto = quarto;
    }

    public Boolean getQuinto() {
        return quinto;
    }

    public void setQuinto(Boolean quinto) {
        this.quinto = quinto;
    }

    public Boolean getSexto() {
        return sexto;
    }

    public void setSexto(Boolean sexto) {
        this.sexto = sexto;
    }

    protected Date getHorario(String hora) throws ParseException {
        return new SimpleDateFormat("HH:mm").parse(hora);
    }
}
