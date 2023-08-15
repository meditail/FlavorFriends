package com.example.flavorfriends.service.impl;

import com.example.flavorfriends.dto.CreateRecipeDto;
import com.example.flavorfriends.exception.ImageNotSavedException;
import com.example.flavorfriends.exception.RecipeNotFoundException;
import com.example.flavorfriends.model.Ingredient;
import com.example.flavorfriends.model.Recipe;
import com.example.flavorfriends.repository.RecipeRepository;
import com.example.flavorfriends.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("jpa")
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if(recipeOpt.isEmpty()) {
            throw new RecipeNotFoundException("Recipe not found");
        }
        return recipeOpt.get();
    }

    @Override
    public Recipe createRecipe(CreateRecipeDto createRecipeDto) {
        String filename = createRecipeDto.getImage().getOriginalFilename();
        MultipartFile image = createRecipeDto.getImage();
        try {
            image.transferTo(new File("images/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageNotSavedException("Image not saved");
        }

        Recipe recipe = Recipe.builder()
                .name(createRecipeDto.getName())
                .description(createRecipeDto.getDescription())
                .photoUrl("images/" + filename)
                .ingredients(
                        createRecipeDto.getIngredientDtos().stream().map(e -> new Ingredient(null, e.getName(), e.getUnit(), e.getQuantity())).collect(Collectors.toList()))
                .postDate(LocalDateTime.now())
                .build();

        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipeById(Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        if(recipeOpt.isEmpty()) {
            throw new RecipeNotFoundException("Recipe not found");
        }
        recipeRepository.delete(recipeOpt.get());
    }
}
