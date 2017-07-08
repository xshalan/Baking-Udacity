package app.com.example.shalan.bakingudacity.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Step;
import app.com.example.shalan.bakingudacity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailsFragment extends Fragment {
    private SimpleExoPlayer exoPlayer ;
    private SimpleExoPlayerView exoPlayerView ;

    public StepDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        Intent intent = getActivity().getIntent() ;
        List<Step> stepList = (List<Step>) intent.getSerializableExtra("step_list");
        int Position = intent.getIntExtra("step_position",0);
        Uri url = Uri.parse(stepList.get(Position).getVideoURL()) ;
        Log.v("Step_details",stepList.get(Position).getDescription());
        
        initializePlayer(url) ;
        return view ;
    }

    public void initializePlayer(Uri videoUri){
            if(exoPlayer == null){
                exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),new DefaultTrackSelector(),new DefaultLoadControl()) ;
                exoPlayerView.setPlayer(exoPlayer);
                MediaSource mediaSource = new ExtractorMediaSource(
                        videoUri,
                        new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(),"BakingUdacity")),
                        new DefaultExtractorsFactory(),
                        null,
                        null
                ) ;
                exoPlayer.prepare(mediaSource);
                exoPlayer.setPlayWhenReady(true);
            }

    }

}
