package app.com.example.shalan.bakingudacity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.com.example.shalan.bakingudacity.Fragments.DetailsFragment;

public class DetailsActivity extends AppCompatActivity {
    Fragment fragment ;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,"Details_Fragment",fragment) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = new DetailsFragment();
        if(savedInstanceState != null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState,"Details_Fragment") ;
        }
        if(getResources().getBoolean(R.bool.isTablet)){
            setContentView(R.layout.details_fragments);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.details_fragment_tablet, fragment).commit();
        }else{
            setContentView(R.layout.activity_details);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.details_container, fragment).commit();
        }




    }
}
