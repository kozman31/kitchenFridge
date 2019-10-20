package com.thekitchenfridge.recipes.entities;

import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.recipes.MeasurementUnit;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class RecipeIngredient extends Auditor<String> {

    private String name;
    private Double amount;
    private Long idx;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit mUnit;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MeasurementUnit getmUnit() {
        return mUnit;
    }

    public void setmUnit(MeasurementUnit MUnit) {
        this.mUnit = MUnit;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }
}
