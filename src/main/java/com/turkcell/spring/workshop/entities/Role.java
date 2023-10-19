package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue()
    private int roleId;

    @Column(name = "role_name")
    private String roleName;
}
