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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView exoPlayerView;
    private TextView step_desc;
    private ImageView no_video_image, exo_thumbnail, playbutton;
    int Position;
    List<Step> stepList;
    Uri thumbnail_url;

    private Button previous;
    private Button next;

    public StepDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        no_video_image = (ImageView) view.findViewById(R.id.exo_no_video);
        exo_thumbnail = (ImageView) view.findViewById(R.id.exo_thumbnail);
        playbutton = (ImageView) view.findViewById(R.id.exo_thumbnail_play);
        exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.player_view);
        step_desc = (TextView) view.findViewById(R.id.step_details_desc);
        previous = (Button) view.findViewById(R.id.previous);
        next = (Button) view.findViewById(R.id.next);

        Intent intent = getActivity().getIntent();
        stepList = (List<Step>) intent.getSerializableExtra("step_list");
        Position = intent.getIntExtra("step_position", 0);

        if (stepList == null) {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                stepList = (List<Step>) bundle.getSerializable("step_list");
                Position = bundle.getInt("step_position");
            }
        }
        final Uri url = Uri.parse(stepList.get(Position).getVideoURL());


        step_desc.setText(stepList.get(Position).getDescription());
        thumbnail_url = Uri.parse(stepList.get(Position).getThumbnailURL());
        Log.v("Step_details", stepList.get(Position).getVideoURL());
        initializePlayer(url);


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Position > 0) {
                    Position--;
                    getActivity().setTitle(stepList.get(Position).getShortDescription());
                    step_desc.setText(stepList.get(Position).getDescription());
                    resetExoPlayer();
                    Uri new_url = Uri.parse(stepList.get(Position).getVideoURL());
                    initializePlayer(new_url);
                }else {
                    Toast.makeText(getContext(),"Go to the next step",Toast.LENGTH_SHORT).show(); ;

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Position < stepList.size() - 1) {
                    Position++;
                    getActivity().setTitle(stepList.get(Position).getShortDescription());
                    step_desc.setText(stepList.get(Position).getDescription());
                    resetExoPlayer();
                    Uri new_url = Uri.parse(stepList.get(Position).getVideoURL());
                    initializePlayer(new_url);
                }else {
                    Toast.makeText(getContext(),"It's the last step :)",Toast.LENGTH_SHORT).show(); ;
                }
            }
        });
        return view;
    }

    public void initializePlayer(Uri videoUri) {
        if (exoPlayer == null && stepList.get(Position).getVideoURL().length() > 0) {
            no_video_image.setVisibility(View.INVISIBLE);
            if (thumbnail_url != null && stepList.get(Position).getThumbnailURL().length() > 0) {
                exoPlayerView.setVisibility(View.INVISIBLE);
                exo_thumbnail.setVisibility(View.VISIBLE);
                playbutton.setVisibility(View.VISIBLE);
                Glide.with((Fragment) this).load(thumbnail_url).into(exo_thumbnail);
                playbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        exoPlayerView.setVisibility(View.VISIBLE);
                        exo_thumbnail.setVisibility(View.INVISIBLE);
                        playbutton.setVisibility(View.INVISIBLE);

                    }
                });
            } else {
                exoPlayerView.setVisibility(View.VISIBLE);
                exo_thumbnail.setVisibility(View.INVISIBLE);
                playbutton.setVisibility(View.INVISIBLE);
            }
            DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector();
            DefaultLoadControl loadControl = new DefaultLoadControl();


            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),
                    defaultTrackSelector,
                    loadControl
            );

            exoPlayerView.setPlayer(exoPlayer);
            MediaSource mediaSource = new ExtractorMediaSource(
                    videoUri,
                    new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "BakingUdacity")),
                    new DefaultExtractorsFactory(),
                    null,
                    null
            );
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

        } else {
            exoPlayerView.setVisibility(View.INVISIBLE);
            playbutton.setVisibility(View.INVISIBLE);
            no_video_image.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer.stop();
            exoPlayer = null;
        }


    }

    public void resetExoPlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }

    }


}
