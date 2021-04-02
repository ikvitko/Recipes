package com.recipes.retrofit.controller;

import com.recipes.retrofit.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesController {

    @GET("recipes.json")
    Call<ServerResponse> getAllRecipes();
}
