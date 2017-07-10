package app.com.example.shalan.bakingudacity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import app.com.example.shalan.bakingudacity.Model.Recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context,Recipe recipe, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent intent  = new Intent() ;
        intent.putExtra("recipe",recipe) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT) ;

        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        view.setOnClickPendingIntent(R.id.recipe_widget_view,pendingIntent);

        String description = "" ;
        for(int i=0 ; i< recipe.getIngredients().size() ; i++ ){
            String ingredient = recipe.getIngredients().get(i).getIngredient();
            String measure = recipe.getIngredients().get(i).getMeasure();
            float quantity = recipe.getIngredients().get(i).getQuantity();
            description += "" + (i+1) +". " + quantity +" " + measure + " " + ingredient +"\n" ;
        }
        view.setTextViewText(R.id.recipe_widget_view, "jjjjjjjjj");
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, view);

    }
    public static void updateRecipeWidget(Context context, Recipe recipe, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        // There may be multiple widgets active, so update all of them
                for (int appWidgetId : appWidgetIds) {
                    updateAppWidget(context, recipe ,appWidgetManager, appWidgetId);
                }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RecipeWidgetService.startActinoUpdateWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

