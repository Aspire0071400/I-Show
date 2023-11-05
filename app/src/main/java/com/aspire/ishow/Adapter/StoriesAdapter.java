package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.StoriesModel;
import com.aspire.ishow.R;

import java.util.ArrayList;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.viewHolder>{
    ArrayList<StoriesModel> storyList;
    Context context;
    public StoriesAdapter(ArrayList<StoriesModel> storyList, Context context) {
        this.storyList = storyList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        StoriesModel model1 = storyList.get(position);
        holder.recycler_story_preview.setImageResource(model1.getStory());
        holder.recycler_story_dp.setImageResource(model1.getProfileDP());
        holder.recycler_story_type_indicator.setImageResource(model1.getStoryType());
        holder.recycler_story_usersname.setText(model1.getUsersname());

    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView recycler_story_preview,recycler_story_dp,recycler_story_type_indicator;
        TextView recycler_story_usersname;
        public viewHolder(View itemView) {
            super(itemView);

            recycler_story_preview = itemView.findViewById(R.id.recycler_story_preview);
            recycler_story_dp = itemView.findViewById(R.id.recycler_story_dp);
            recycler_story_type_indicator = itemView.findViewById(R.id.recycler_story_type_indicator);
            recycler_story_usersname = itemView.findViewById(R.id.recycler_story_usersname);
        }
    }
}
