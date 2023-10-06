package com.turkcell.spring.workshop.entities.dtos.Order;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.spring.workshop.entities.dtos.Order_Details.OrderDetailsForAddto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForAddDto {

    @NotBlank
    private String customerID;

    @NotNull
    private int employeeId;
    private LocalDate requiredDate;
    private short shipVia;
    private String shipName;
    private String shipAddress;
    private float freight;
    private String shipCity;
    private String shipRegion;

    private List<OrderDetailsForAddto> items;
}
