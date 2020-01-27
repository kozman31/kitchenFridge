package com.thekitchenfridge.pantry.entity;

import com.thekitchenfridge.recipes.MeasurementUnit;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class PantryItem {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String brand;
    private long upc;
    private String notes;
    private LocalDate purchaseDate;
    private double totalStocked;
    private MeasurementUnit mUnit;

}
