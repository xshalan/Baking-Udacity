package app.com.example.shalan.bakingudacity.Utils;

import android.view.View;

import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Step;

/**
 * Created by noura on 08/07/2017.
 */

public interface OnStepClickListener {
    void onStepClick(View view, List<Step> stepList, int Position);
}
