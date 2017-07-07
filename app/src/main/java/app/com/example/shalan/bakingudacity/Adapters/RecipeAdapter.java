package app.com.example.shalan.bakingudacity.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Recipe;
import app.com.example.shalan.bakingudacity.R;
import app.com.example.shalan.bakingudacity.Utils.OnRecipeClickListener;

/**
 * Created by noura on 07/07/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> mRecipeList = new ArrayList<Recipe>();
    private Context mContext;
    OnRecipeClickListener mOnRecipeClickListener ;

    public RecipeAdapter(Context mContext,OnRecipeClickListener onRecipeClickListener){
        this.mContext= mContext ;
        mOnRecipeClickListener = onRecipeClickListener ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recipe_card_view,parent,false) ;
        ViewHolder mRecipeViewHolder = new ViewHolder(view) ;

         return mRecipeViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe mRecipe = mRecipeList.get(position);
        holder.recipe_name.setText(mRecipe.getName());
        holder.ingredients.setText(Integer.toString(mRecipe.getIngredients().size()) );
        holder.serving.setText(Integer.toString(mRecipe.getServings()));
        holder.step.setText(Integer.toString(mRecipe.getSteps().size()) );

    }

    @Override
    public int getItemCount() {

        return mRecipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Recipe mRecipe ;
        //@BindView(R.id.recipe_name)
        TextView recipe_name ;
        //@BindView(ingredients)
        TextView ingredients ;
        //@BindView(R.id.serving)
        TextView serving ;
        //@BindView(R.id.steps)
        TextView step ;
        public ViewHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(this,itemView) ;
            recipe_name = (TextView) itemView.findViewById(R.id.recipe_name);
            ingredients  = (TextView) itemView.findViewById(R.id.ingredients);
            serving = (TextView) itemView.findViewById(R.id.serving);
            step = (TextView) itemView.findViewById(R.id.steps);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(mOnRecipeClickListener!=null) {
                mOnRecipeClickListener.onClick(view, getAdapterPosition());
                Log.v("Main","Testing");
            }

        }
    }
    public void setmRecipeList(List<Recipe> recipeList){
        this.mRecipeList = recipeList ;
    }
}
