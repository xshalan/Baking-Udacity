package app.com.example.shalan.bakingudacity.Utils;

import android.view.View;

import java.util.ArrayList;

import app.com.example.shalan.bakingudacity.Model.Recipe;

/**
 * Created by noura on 07/07/2017.
 */

public interface OnRecipeClickListener {
    void onClick(View view, ArrayList<Recipe> recipeList, int Position) ;
}
