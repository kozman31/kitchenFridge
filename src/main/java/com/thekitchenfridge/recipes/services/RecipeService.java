package com.thekitchenfridge.recipes.services;

import com.thekitchenfridge.recipes.entities.Recipe;
import com.thekitchenfridge.recipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {


    private RecipeRepository recipeRepository;

    public RecipeService (RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public void saveRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(long id) {
        return recipeRepository.getOne(id);
    }

    public List<Recipe> getRecipesByContributor(String contributor) {
        return recipeRepository.findByContributor(contributor);
    }

    public List<Recipe> getAllRecipes (){
        return recipeRepository.findAll();
    }
}
