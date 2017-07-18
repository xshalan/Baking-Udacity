package app.com.example.shalan.bakingudacity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.com.example.shalan.bakingudacity.Fragments.MainFragment;
import app.com.example.shalan.bakingudacity.Utils.MainActivityIdlingResource;



public class MainActivity extends AppCompatActivity {

    @Nullable
    private MainActivityIdlingResource mMainActivityIdlingResource;


    @Override
    protected void onStart() {
        super.onStart();
        getIdlingResource();
        if (mMainActivityIdlingResource != null) {
            mMainActivityIdlingResource.setIdleState(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_container, fragment).commit();


    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mMainActivityIdlingResource == null)
            mMainActivityIdlingResource = new MainActivityIdlingResource();

        return mMainActivityIdlingResource;
    }
}
