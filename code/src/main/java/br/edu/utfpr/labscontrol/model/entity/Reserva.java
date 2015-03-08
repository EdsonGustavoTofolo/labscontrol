package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 06/12/2014.
 */
@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

}
