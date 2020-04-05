package com.thekitchenfridge.recipes.entities;

import com.thekitchenfridge.audit.Auditor;

import javax.persistence.Embeddable;

@Embeddable
public class RecipeIngredient extends Auditor<String> {

    private String name;
    private String quantity;
    private Long idx;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }
}
