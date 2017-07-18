package app.com.example.shalan.bakingudacity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Ingredient;

import static app.com.example.shalan.bakingudacity.R.drawable.recipe;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);

        String json = sharedPreferences.getString("List", "");
        String recipe_name = sharedPreferences.getString("Name", "");
        Type listType = new TypeToken<List<Ingredient>>() {
        }.getType();

        List<Ingredient> ingredients = gson.fromJson(json, listType);
        Log.v("RecipeWidgetProvider", ingredients.toString());

        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("recipe", recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        view.setOnClickPendingIntent(R.id.recipe_widget_view, pendingIntent);

        String description = "";
        for (int i = 0; i < ingredients.size(); i++) {
            String ingredient = ingredients.get(i).getIngredient();
            String measure = ingredients.get(i).getMeasure();
            float quantity = ingredients.get(i).getQuantity();
            description += " *  " + quantity + " " + measure + " " + ingredient + "\n";
        }
        if (description != null && recipe_name != null) {
            view.setTextViewText(R.id.text_ingre_list, description);
            view.setTextViewText(R.id.text_ingre_name, recipe_name);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, view);

    }

    public static void updateRecipeWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

