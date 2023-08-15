package com.example.flavorfriends.controller;

import com.example.flavorfriends.dto.CreateRecipeDto;
import com.example.flavorfriends.model.Recipe;
import com.example.flavorfriends.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(@Qualifier("jpa") RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody CreateRecipeDto createRecipeDto) {
        return new ResponseEntity<>(recipeService.createRecipe(createRecipeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRecipeById(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
