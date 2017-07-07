package app.com.example.shalan.bakingudacity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

import app.com.example.shalan.bakingudacity.Adapters.RecipeAdapter;
import app.com.example.shalan.bakingudacity.Model.Recipe;
import app.com.example.shalan.bakingudacity.Network.RecipeAPI;
import app.com.example.shalan.bakingudacity.Utils.OnRecipeClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.com.example.shalan.bakingudacity.Utils.ApiUtils.getRecipes;


public class MainActivity extends AppCompatActivity implements OnRecipeClickListener{
//    @BindView(R.id.recipe_recyclerView)
//    RecyclerView Recipe_recyclerView ;
    RecipeAdapter recipeAdapter ;

    private RecipeAPI mRecipeAPI ;

    RecyclerView Recipe_recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.bind(this);
        Recipe_recyclerView = (RecyclerView) findViewById(R.id.recipe_recyclerView);
        recipeAdapter = new RecipeAdapter(getApplicationContext(),this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Recipe_recyclerView.setLayoutManager(layoutManager);

        mRecipeAPI = getRecipes() ;
        Call<List<Recipe>> call = mRecipeAPI.getRecipes() ;
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.v("Main","Success!" + response.body().size()) ;
                recipeAdapter.setmRecipeList(response.body());
                recipeAdapter.notifyDataSetChanged();
                Recipe_recyclerView.setAdapter(recipeAdapter);



            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.v("Main","Failed!" + t.toString()) ;

            }
        });
    }
    public void getRecipe(){
        Call<List<Recipe>> call = mRecipeAPI.getRecipes() ;
        try {
            List<Recipe> recipeList = call.execute().body() ;
            Log.v("Main",recipeList.toString()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view, int Position) {
        Log.v("Main",Integer.toString(Position));
    }
}
