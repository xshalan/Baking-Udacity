package app.com.example.shalan.bakingudacity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by noura on 20/07/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityIntentTest {

    @Rule
    public ActivityTestRule<MainActivity> intentsTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Intents.init();
    }


    @Test
    public void testOnClickRecipeRecyclerView_openDetailsActivity(){
        onView(withId(R.id.recipe_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        intended(hasComponent(DetailsActivity.class.getName()));
        intended(hasExtraWithKey("list"));

    }

    @After
    public void tearDown() {
        Intents.release();
    }
}
