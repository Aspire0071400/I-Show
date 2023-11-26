package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.StoriesModel;
import com.aspire.ishow.Model.StoryViewModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.R;
import com.devlomi.circularstatusview.CircularStatusView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import omari.hamza.storyview.StoryView;
import omari.hamza.storyview.callback.StoryClickListeners;
import omari.hamza.storyview.model.MyStory;

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
        StoriesModel story = storyList.get(position);
        if (story.getStories().size() > 0){
            StoryViewModel lastStory = story.getStories().get(story.getStories().size() - 1);
        Picasso.get().load(lastStory.getImage()).into(holder.recyclerStoryPreview);
        holder.view9.setPortionsCount(story.getStories().size());

        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(story.getStoryBy()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder).into(holder.recycler_story_dp);
                        holder.recycler_story_usersname.setText(user.getName());

                        holder.recyclerStoryPreview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<MyStory> myStories = new ArrayList<>();

                                for (StoryViewModel storyViewModel : story.getStories()) {
                                    myStories.add(new MyStory(storyViewModel.getImage()));
                                }

                                //Ctusom Library from github for storyView.
                                new StoryView.Builder(((AppCompatActivity) context).getSupportFragmentManager())
                                        .setStoriesList(myStories) // Required
                                        .setStoryDuration(10000) // Default is 2000 Millis (2 Seconds)
                                        .setTitleText(user.getName()) // Default is Hidden
                                        .setSubtitleText("") // Default is Hidden
                                        .setTitleLogoUrl(user.getProfile()) // Default is Hidden
                                        .setStoryClickListeners(new StoryClickListeners() {
                                            @Override
                                            public void onDescriptionClickListener(int position) {
                                                //your action
                                            }

                                            @Override
                                            public void onTitleIconClickListener(int position) {
                                                //your action
                                            }
                                        }) // Optional Listeners
                                        .build() // Must be called before calling show method
                                        .show();

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        }

    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView recyclerStoryPreview,recycler_story_type_indicator,recycler_story_dp;
        TextView recycler_story_usersname;
        CircularStatusView view9;

        public viewHolder(View itemView) {
            super(itemView);
            recyclerStoryPreview = itemView.findViewById(R.id.recycler_story_preview);
            recycler_story_type_indicator = itemView.findViewById(R.id.recycler_story_type_indicator);
            recycler_story_dp = itemView.findViewById(R.id.recycler_story_dp);
            recycler_story_usersname = itemView.findViewById(R.id.recycler_story_usersname);
            view9 = itemView.findViewById(R.id.view9);


            
        }
    }
}
