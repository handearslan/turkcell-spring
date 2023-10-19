package com.turkcell.spring.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column(name="id")
    @GeneratedValue()
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    //@Data bu 2 field ın getter ve setter larını oluşturduğu için
    //UserDetails te bulunan zorunlu metodlardan getUsername ve getPassword oluşturmaya gerek kalmadı.
    //Eğer isimleri farklı olsaydı bulamayacaktı ve implement etme zorunluluğu getirecekti veya UserDetails te metodu oluşturup
    // return de istediğimiz değeri döndürecektik.

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne()
    @JoinColumn(name="role_id")
    @JsonIgnore
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // roller
        // todo: refactor with multiple roles
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    //Yetkili lerin çekildiği metoddur
    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // roller
        // todo: refactor with multiple roles

        //Bir veya birden fazla rol return edilebilir.
        //İlerde farklı bir tablodan (veri tabanı) çekilecek.
        //return List.of(new SimpleGrantedAuthority(role), new SimpleGrantedAuthority("Deneme"));
        return List.of(new SimpleGrantedAuthority(role));
    }*/


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
}