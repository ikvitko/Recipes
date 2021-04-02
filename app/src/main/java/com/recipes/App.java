package com.recipes;

import androidx.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.recipes.retrofit.controller.RecipesController;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends MultiDexApplication{

    public static final String SERVER_URL = "https://test.kode-t.ru/";
    private Retrofit retrofit;

    private static RecipesController RecipesController;

    public static RecipesController getCurrencyController() {
        return RecipesController;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RecipesController = retrofit.create(RecipesController.class);
    }
}
