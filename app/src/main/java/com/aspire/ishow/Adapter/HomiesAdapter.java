package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.HomiesModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomiesAdapter extends RecyclerView.Adapter<HomiesAdapter.viewHolder> {

    ArrayList<HomiesModel> homieList;
    Context context;

    public HomiesAdapter(ArrayList<HomiesModel> homieList, Context context) {
        this.homieList = homieList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_homies_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HomiesModel model3  = homieList.get(position);
        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(model3.getFollowedBy()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Picasso.get().load(user.getProfile())
                                .placeholder(R.drawable.profileplaceholder)
                                .into(holder.profileHomiesDpIv);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return homieList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView profileHomiesDpIv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profileHomiesDpIv = itemView.findViewById(R.id.profile_homie_dp_iv);

        }
    }
}
