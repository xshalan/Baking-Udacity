package app.com.example.shalan.bakingudacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Step;

public class StepDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_details_activity);
        Intent intent = getIntent() ;
        List<Step> stepList = (List<Step>) intent.getSerializableExtra("step_list");
        int Position = intent.getIntExtra("step_position",0);
        Log.v("Step_details",stepList.get(Position).getDescription());
    }
}
