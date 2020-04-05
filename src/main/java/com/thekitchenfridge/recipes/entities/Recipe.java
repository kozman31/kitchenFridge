package com.thekitchenfridge.recipes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thekitchenfridge.audit.Auditor;
import com.thekitchenfridge.users.entity.User;

import javax.persistence.*;
import java.util.List;

/*
{
  "recipeName": "Eggless Brownies",
  "description": "No eggs, tastes like ",
  "instructions": "Step 1)\nStep 2)\ndone",
  "ingredientList": {
    "0": {
      "name": "beans",
      "quantity": "1 can"
    },
    "1": {
      "name": "flour",
      "quantity": "12 lbs"
    }
  }
}
 */

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recipe extends Auditor<String> {

    @Id
    @GeneratedValue
    private Long id;
    private String recipeName;
    private String description;
    private String instructions;
    private Boolean isPublic;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_user_id")
    private User user;

    @ElementCollection
   // @CollectionTable(name="IngredientList")
    private List<RecipeIngredient> ingredientList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RecipeIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RecipeIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                '}';
    }
}
