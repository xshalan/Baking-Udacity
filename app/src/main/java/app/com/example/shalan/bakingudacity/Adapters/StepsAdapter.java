package app.com.example.shalan.bakingudacity.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.com.example.shalan.bakingudacity.Model.Step;
import app.com.example.shalan.bakingudacity.R;
import app.com.example.shalan.bakingudacity.Utils.OnStepClickListener;

/**
 * Created by noura on 08/07/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<Step> mSteps = new ArrayList<Step>();
    private Context mContext;
    private OnStepClickListener mOnStepClickListener ;

    public StepsAdapter(Context mContext,OnStepClickListener mOnStepClickListener){
        this.mContext = mContext ;
        this.mOnStepClickListener = mOnStepClickListener ;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.step_card_view,parent,false) ;
        ViewHolder mStepViewHolder = new ViewHolder(view) ;
        return mStepViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Step step = mSteps.get(position);
        holder.step_id.setText(Integer.toString(step.getId()));
        holder.step_desc.setText(step.getShortDescription());
        if(mSteps.get(position).getVideoURL().length() != 0) {
            Glide.with(mContext).load(R.drawable.play).into(holder.step_video_image);
        }
        Log.v("length",String.valueOf(mSteps.get(position).getVideoURL().length()));
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView step_id ;
        private TextView step_desc;
        private ImageView step_video_image ;
        public ViewHolder(View itemView) {
            super(itemView);
            step_id = (TextView) itemView.findViewById(R.id.step_id);
            step_desc = (TextView) itemView.findViewById(R.id.step_description);
            step_video_image = (ImageView) itemView.findViewById(R.id.step_video);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnStepClickListener.onStepClick(view,mSteps,getAdapterPosition());
        }
    }
    public void setStepsList(List<Step> mSteps){
        this.mSteps = mSteps ;
    }
}
