package com.turkcell.spring.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @Column(name="id")
    @GeneratedValue()
    private Integer id;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;

}
