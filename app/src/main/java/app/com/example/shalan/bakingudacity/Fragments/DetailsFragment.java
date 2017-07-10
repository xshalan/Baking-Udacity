package app.com.example.shalan.bakingudacity.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.com.example.shalan.bakingudacity.Adapters.StepsAdapter;
import app.com.example.shalan.bakingudacity.Model.Ingredient;
import app.com.example.shalan.bakingudacity.Model.Recipe;
import app.com.example.shalan.bakingudacity.Model.Step;
import app.com.example.shalan.bakingudacity.R;
import app.com.example.shalan.bakingudacity.StepDetailsActivity;
import app.com.example.shalan.bakingudacity.Utils.OnStepClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements OnStepClickListener{

    TextView ingredient_card ;
    private RecyclerView steps_recyclerView;
    private StepsAdapter stepAdapter;
     ;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ingredient_card = (TextView) view.findViewById(R.id.ingredient_card_text);
        Intent intent = getActivity().getIntent();
        ArrayList<Recipe> recipeList = (ArrayList<Recipe>) intent.getSerializableExtra("list");

        int Position = intent.getIntExtra("position",0) ;
        List<Ingredient> ingredients= recipeList.get(Position).getIngredients();
        List<Step> Steps = recipeList.get(Position).getSteps();
        setIngredientCard(ingredients,ingredient_card);

        steps_recyclerView = (RecyclerView) view.findViewById(R.id.steps_recyclerview);
        stepAdapter = new StepsAdapter(getContext(),this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        steps_recyclerView.setLayoutManager(layoutManager);
        stepAdapter.setStepsList(Steps);
        steps_recyclerView.setAdapter(stepAdapter);

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
}
