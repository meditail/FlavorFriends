package com.example.flavorfriends.service;

import com.example.flavorfriends.dto.CreateRecipeDto;
import com.example.flavorfriends.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
    Recipe createRecipe(CreateRecipeDto createRecipeDto);
    void deleteRecipeById(Long id);

}
