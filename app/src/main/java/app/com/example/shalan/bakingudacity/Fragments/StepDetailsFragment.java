package app.com.example.shalan.bakingudacity.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.example.shalan.bakingudacity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailsFragment extends Fragment {


    public StepDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);


        return view ;
    }

}
