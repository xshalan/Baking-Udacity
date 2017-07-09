package app.com.example.shalan.bakingudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import app.com.example.shalan.bakingudacity.Fragments.StepDetailsFragment;
import app.com.example.shalan.bakingudacity.Model.Step;

public class StepDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.step_details_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent() ;
        List<Step> stepList = (List<Step>) intent.getSerializableExtra("step_list");
        int Position = intent.getIntExtra("step_position",0);
        getSupportActionBar().setTitle(stepList.get(Position).getShortDescription());

        Fragment fragment = new StepDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.step_details_container, fragment).commit();
    }

}
