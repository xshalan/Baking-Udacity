package app.com.example.shalan.bakingudacity.Network;

import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by noura on 05/07/2017.
 */

public interface RecipeAPI {
    @GET(" ")
    Call<List<Recipe>> getRecipes() ;





}
