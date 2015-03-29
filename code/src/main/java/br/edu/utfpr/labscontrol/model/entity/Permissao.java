package br.edu.utfpr.labscontrol.model.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by EdsonGustavo on 22/03/2015.
 */
@Entity
@Table(name = "permissoes")
public class Permissao implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 50, nullable = false)
    private String permissao;

    public Permissao() {
    }

    public Permissao(String permissao) {
        this.permissao = permissao;
    }

    @Override
    public String getAuthority() {
        return getPermissao();
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Permissao)){
            return false;
        }
        Permissao bean = (Permissao)obj;
        return new EqualsBuilder().append(bean.getPermissao(), this.getPermissao()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getPermissao()).toHashCode();
    }
}
