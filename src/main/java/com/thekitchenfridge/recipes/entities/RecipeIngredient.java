package com.thekitchenfridge.recipes.entities;

import com.thekitchenfridge.audit.Auditor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class RecipeIngredient extends Auditor<String> {

    public enum MUnit {
        TSP, TBSP, FLOZ, CUP, PINT, QUART, GAL, ML, L, DL, LB, OZ, MG, GRAM, KG, MM, CM, M, INCH
    }

    private String name;
    private Double amount;
    private Long idx;

    @Enumerated(EnumType.STRING)
    private MUnit mUnit;


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

    public MUnit getmUnit() {
        return mUnit;
    }

    public void setmUnit(MUnit MUnit) {
        this.mUnit = MUnit;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }
}
