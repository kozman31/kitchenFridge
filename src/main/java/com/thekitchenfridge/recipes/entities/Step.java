package com.thekitchenfridge.recipes.entities;

import javax.persistence.*;

@Embeddable
public class Step {

    private Integer stepNumber;
    private String instructions;

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
