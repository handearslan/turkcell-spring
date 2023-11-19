package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_territories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTerritory {

    @Id
    @ManyToOne()
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne()
    @MapsId("territoryId")
    @JoinColumn(name = "territory_id")
    private Territory territory;
}