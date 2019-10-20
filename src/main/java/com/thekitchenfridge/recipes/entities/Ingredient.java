package com.thekitchenfridge.recipes.entities;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.pantry.entity.PantryItem;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;

@Data
public class Ingredient extends Auditor<String> {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String brand;
    private List<String> substitutes;
    private Boolean inStock;

    @OneToOne(mappedBy = "id")
    PantryItem pantryItemId;
}
