package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by EdsonGustavo on 06/12/2014.
 */
@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioId", referencedColumnName = "id")
    private Usuario usuario;
    @Column(length = 60)
    private String outroUsuario;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;
    @Column
    private Boolean confirmada;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ambienteId", referencedColumnName = "id")
    private Ambiente ambiente;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date horaInicio;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date horaFim;
    @OneToMany(mappedBy = "reserva", targetEntity = ReservaItem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservaItem> reservasItens;
    @Column(length = 255)
    private String observacao;

    public Reserva() {
    }

    public Reserva(Usuario usuario, String outroUsuario, Date data, Boolean confirmada, Ambiente ambiente, Date horaInicio, Date horaFim, List<ReservaItem> reservasItens, String observacao) {
        this.usuario = usuario;
        this.outroUsuario = outroUsuario;
        this.data = data;
        this.confirmada = confirmada;
        this.ambiente = ambiente;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.reservasItens = reservasItens;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getOutroUsuario() {
        return outroUsuario;
    }

    public void setOutroUsuario(String outroUsuario) {
        this.outroUsuario = outroUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Boolean confirmada) {
        this.confirmada = confirmada;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public List<ReservaItem> getReservasItens() {
        return reservasItens;
    }

    public void setReservasItens(List<ReservaItem> reservasItens) {
        this.reservasItens = reservasItens;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserva reserva = (Reserva) o;

        if (id != null ? !id.equals(reserva.id) : reserva.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
