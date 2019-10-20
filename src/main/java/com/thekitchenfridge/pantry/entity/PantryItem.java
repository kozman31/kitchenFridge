package com.thekitchenfridge.pantry.entity;

import com.thekitchenfridge.recipes.MeasurementUnit;
import com.thekitchenfridge.recipes.entities.Ingredient;
import com.thekitchenfridge.recipes.entities.RecipeIngredient;
import io.micrometer.core.instrument.Measurement;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
