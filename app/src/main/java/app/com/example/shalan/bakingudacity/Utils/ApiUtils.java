package app.com.example.shalan.bakingudacity.Utils;

import app.com.example.shalan.bakingudacity.Network.RecipeAPI;
import app.com.example.shalan.bakingudacity.Network.RetrofitClient;

/**
 * Created by noura on 05/07/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "http://go.udacity.com/android-baking-app-json/";

    public static RecipeAPI getRecipes(){
        return RetrofitClient.getClient(BASE_URL).create(RecipeAPI.class) ;
    }

}
