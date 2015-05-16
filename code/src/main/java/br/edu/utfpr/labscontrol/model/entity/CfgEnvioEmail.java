package br.edu.utfpr.labscontrol.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EdsonGustavo on 16/05/2015.
 */
@Entity
@Table(name = "cfg_envio_email")
public class CfgEnvioEmail implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, length = 100)
    private String hostName;
    @Column(nullable = false)
    private Integer port;
    @Column(length = 256, nullable = false)
    private String email;
    @Column(length = 256, nullable = false)
    private String senha;
    @Column
    private Boolean ativa;

    public CfgEnvioEmail(String hostName, Integer port, Boolean ativa) {
        this.hostName = hostName;
        this.port = port;
        this.ativa = ativa;
    }

    public CfgEnvioEmail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CfgEnvioEmail that = (CfgEnvioEmail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

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
