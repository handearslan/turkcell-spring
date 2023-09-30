package com.turkcell.spring.workshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "territories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Territory {
    @Id
    @Column(name = "territory_id")
    private String territoryId;

    @Column(name = "territory_description")
    private String territoryDescription;

    @OneToMany(mappedBy = "territory")
    private List<EmployeeTerritory> EmployeeTerritories;

    @ManyToOne()
    @JoinColumn(name = "region_id")
    private Region region;
}
