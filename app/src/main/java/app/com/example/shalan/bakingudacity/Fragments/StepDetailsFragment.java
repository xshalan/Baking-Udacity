package app.com.example.shalan.bakingudacity.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView step_desc ;

    int Position ;
    List<Step> stepList;

    private Button previous ;
    private Button next ;

    public StepDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        Intent intent = getActivity().getIntent() ;
        stepList = (List<Step>) intent.getSerializableExtra("step_list");
        Position = intent.getIntExtra("step_position",0);
        Uri url = Uri.parse(stepList.get(Position).getVideoURL()) ;

        exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.player_view);
        step_desc = (TextView)  view.findViewById(R.id.step_details_desc);
        previous = (Button) view.findViewById(R.id.previous);
        next = (Button) view.findViewById(R.id.next);
        step_desc.setText(stepList.get(Position).getDescription());
        Log.v("Step_details",stepList.get(Position).getVideoURL());
        initializePlayer(url) ;

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Position > 0 ){
                    Position--;
                    step_desc.setText(stepList.get(Position).getDescription());
                    resetExoPlayer() ;
                    Uri new_url = Uri.parse(stepList.get(Position).getVideoURL()) ;
                    initializePlayer(new_url) ;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Position <stepList.size() -1 ){
                    Position++;
                    step_desc.setText(stepList.get(Position).getDescription());
                    resetExoPlayer() ;
                    Uri new_url = Uri.parse(stepList.get(Position).getVideoURL()) ;
                    initializePlayer(new_url) ;
                }
            }
        });
        return view ;
    }

    public void initializePlayer(Uri videoUri){
            if(exoPlayer == null){
                DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector() ;
                DefaultLoadControl loadControl = new DefaultLoadControl();


                exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),
                        defaultTrackSelector,
                        loadControl
                ) ;

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
    public void resetExoPlayer(){
        exoPlayer.seekTo(0);
        exoPlayer.setPlayWhenReady(false);

    }

}
