package com.thekitchenfridge.recipes.conrollers;

import com.thekitchenfridge.recipes.entities.Recipe;
import com.thekitchenfridge.recipes.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public void getRecipeByName() {
    }


    public void searchByCusine() {
    }

    @PostMapping("/addRecipe")
    public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
        System.out.println("adding: ");
        recipeService.saveRecipe(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getRecipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        System.out.println("getting " + id);

        Recipe recipe = recipeService.getRecipeById(id);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<Recipe>> updateRecipe() {
        System.out.println("getting all");

        List<Recipe> list = recipeService.getAllRecipes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{contributor}/getRecipes")
    public ResponseEntity<List<Recipe>> getRecipesByContributor(@PathVariable("contributor") String contributor) {
        System.out.println("getting recipes from " + contributor);

        List<Recipe> list = recipeService.getRecipesByContributor(contributor);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

