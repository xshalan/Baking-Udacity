package app.com.example.shalan.bakingudacity.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.com.example.shalan.bakingudacity.Adapters.StepsAdapter;
import app.com.example.shalan.bakingudacity.Model.Ingredient;
import app.com.example.shalan.bakingudacity.Model.Recipe;
import app.com.example.shalan.bakingudacity.Model.Step;
import app.com.example.shalan.bakingudacity.R;
import app.com.example.shalan.bakingudacity.RecipeWidgetService;
import app.com.example.shalan.bakingudacity.StepDetailsActivity;
import app.com.example.shalan.bakingudacity.Utils.OnStepClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements OnStepClickListener{

    TextView ingredient_card ;
    private RecyclerView steps_recyclerView;
    private StepsAdapter stepAdapter;
    List<Ingredient> ingredients ;
    ArrayList<Recipe> recipeList ;
    int Position ;
            ;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.details_activity_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_widget :
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("pref",Context.MODE_PRIVATE) ;
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                setList("List",ingredients,editor);
                String recipe_name = recipeList.get(Position).getName() ;
                editor.putString("Name",recipe_name).commit();
                Toast.makeText(getContext(),"Added to widget",Toast.LENGTH_SHORT).show();

                break;
            default:
                Log.v("DetailsFragment:","Nothing is clicked!") ;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ingredient_card = (TextView) view.findViewById(R.id.ingredient_card_text);
        Intent intent = getActivity().getIntent();
        recipeList = (ArrayList<Recipe>) intent.getSerializableExtra("list");

        Position = intent.getIntExtra("position",0) ;
        ingredients= recipeList.get(Position).getIngredients();
        List<Step> Steps = recipeList.get(Position).getSteps();
        setIngredientCard(ingredients,ingredient_card);

        steps_recyclerView = (RecyclerView) view.findViewById(R.id.steps_recyclerview);
        stepAdapter = new StepsAdapter(getContext(),this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        steps_recyclerView.setLayoutManager(layoutManager);
        stepAdapter.setStepsList(Steps);
        steps_recyclerView.setAdapter(stepAdapter);

        /**
         * Testing section for Widget >_<
         */


        int W = 1 ;
        if(W == 0){
            Intent intentServiceWidget = new Intent(getActivity(), RecipeWidgetService.class) ;
            Bundle bundle = new Bundle() ;
            bundle.putString("test","Hello world");
            intent.putExtra("test","Hello world") ;
            //getContext().startService(intentServiceWidget);
            Log.v("Service","Starting service") ;
        }

        return view ;
    }

    public void setIngredientCard(List<Ingredient> ingredients, TextView card){
         String description = "" ;
        for(int i=0 ; i< ingredients.size() ; i++ ){
            String ingredient = ingredients.get(i).getIngredient();
            String measure = ingredients.get(i).getMeasure();
            float quantity = ingredients.get(i).getQuantity();
           description += "" + (i+1) +". " + quantity +" " + measure + " " + ingredient +"\n" ;
        }
        card.setText(description);
    }

    @Override
    public void onStepClick(View view, List<Step> stepList, int Position) {
        if(getResources().getBoolean(R.bool.isTablet)){
            Bundle bundle = new Bundle();
            bundle.putSerializable("step_list", (Serializable) stepList);
            bundle.putInt("step_position", Position);
            Fragment fragment = new StepDetailsFragment();
            fragment.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .add(R.id.step_details_fragment_tablet, fragment)
                    .commit();
        }else
            {
            Intent intent = new Intent(getActivity(), StepDetailsActivity.class);
            intent.putExtra("step_list", (Serializable) stepList);
            intent.putExtra("step_position", Position);
            getActivity().startActivity(intent);
            Log.v("Step", "Clicked " + Position);
        }
    }

    public void setList(String key, List<Ingredient> list,SharedPreferences.Editor editor) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        set(key, json,editor);
    }

    public static void set(String key, String value,SharedPreferences.Editor editor) {
        editor.putString(key, value);
        editor.commit();
    }
}
