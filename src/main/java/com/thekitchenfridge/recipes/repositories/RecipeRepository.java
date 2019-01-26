package com.thekitchenfridge.recipes.repositories;

import com.thekitchenfridge.recipes.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    //Select name, id from recipe where contributor = "koz"; get user recipes

    @Query(value="Select * from recipe where contributor = ?1", nativeQuery = true)
    List<Recipe> findByContributor (String contributor);
}