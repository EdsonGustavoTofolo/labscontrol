package br.edu.utfpr.labscontrol.model.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable, UserDetails {
	private static final BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();

    @Id
	@Column(name = "id")
    @GeneratedValue
    private Integer id;
    @Column(length = 80, nullable = false)
    private String nome;
    @Column(length = 80, nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Permissao> permissoes;


    public Usuario() {

    }

    public Usuario(String nome, String username, String password, String email, Set<Permissao> permissoes) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.permissoes = permissoes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auto = new ArrayList<>();
        auto.addAll(getPermissoes());
        return auto;
    }

    public void addPermissao(Permissao permissao){
        if(permissoes == null){
            permissoes = new HashSet<Permissao>();
        }
        permissoes.add(permissao);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodePassword(String password) {
        if (StringUtils.isNotEmpty(password)) {
            return bcry.encode(password);
        }
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Usuario)){
            return false;
        }
        Usuario bean = (Usuario)obj;
        return new EqualsBuilder().append(bean.getUsername(), this.getUsername()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getUsername()).toHashCode();
    }
}
