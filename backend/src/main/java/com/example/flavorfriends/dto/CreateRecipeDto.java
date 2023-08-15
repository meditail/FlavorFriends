package com.example.flavorfriends.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeDto {

    private String name;
    private String description;
    private MultipartFile image;
    private List<CreateIngredientDto> ingredientDtos;

}