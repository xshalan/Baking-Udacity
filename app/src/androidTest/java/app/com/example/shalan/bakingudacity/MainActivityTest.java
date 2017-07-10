package app.com.example.shalan.bakingudacity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by noura on 09/07/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private IdlingResource mIdlingResource ;
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTest = new ActivityTestRule<MainActivity>(MainActivity.class) ;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mainActivityTest.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void testClickonRecipeRecyclerview_OpenDetailsActivity(){
        onView(withId(R.id.recipe_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.ingredient_card_text))
                .check(matches(isDisplayed())) ;
    }

    @After
    public void unregisterIdlingResource() {
        if(mIdlingResource != null )
            Espresso.unregisterIdlingResources(mIdlingResource);
    }
}
