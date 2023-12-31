package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.HomiesModel;
import com.aspire.ishow.Model.NotificationModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.viewHolder>{
    Context context;
    ArrayList<User> userList;
    public SearchAdapter(ArrayList<User> userList,Context context) {
        this.userList = userList;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        User user = userList.get(position);
        Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder).into(holder.searchPicIv);
        holder.searchNameTv.setText(user.getName());
        holder.searchProfessionTv.setText(user.getProfession());

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(user.getUserID())
                .child("followers")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    holder.searchFollowBtn.setBackground(ContextCompat.getDrawable(context,R.drawable.following_btn_bg));
                    holder.searchFollowBtn.setText("FOLLOWING");
                    holder.searchFollowBtn.setTextColor(context.getResources().getColor(R.color.grey));
                    holder.searchFollowBtn.setEnabled(false);
                }else {
                    holder.searchFollowBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HomiesModel homiesModel = new HomiesModel();
                            homiesModel.setFollowedBy(FirebaseAuth.getInstance().getUid());
                            homiesModel.setFollowedAt(new Date().getTime());

                            FirebaseDatabase.getInstance().getReference()
                                    .child("Users")
                                    .child(user.getUserID())
                                    .child("followers")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .setValue(homiesModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Users")
                                                    .child(user.getUserID())
                                                    .child("followerCount")
                                                    .setValue(user.getFollowerCount() + 1 ).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            holder.searchFollowBtn.setBackground(ContextCompat.getDrawable(context,R.drawable.following_btn_bg));
                                                            holder.searchFollowBtn.setText("FOLLOWING");
                                                            holder.searchFollowBtn.setTextColor(context.getResources().getColor(R.color.grey));
                                                            holder.searchFollowBtn.setEnabled(false);
                                                            Toast.makeText(context,"You Followed" +user.getName(),Toast.LENGTH_SHORT).show();

                                                            NotificationModel notificationModel = new NotificationModel();
                                                            notificationModel.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                            notificationModel.setNotificationAt(new Date().getTime());
                                                            notificationModel.setType("follow");

                                                            FirebaseDatabase.getInstance().getReference()
                                                                    .child("notification")
                                                                    .child(user.getUserID())
                                                                    .push().setValue(notificationModel);

                                                        }
                                                    });
                                        }
                                    });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView searchNameTv,searchProfessionTv;
        ImageView searchPicIv;
        Button searchFollowBtn;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            searchPicIv = itemView.findViewById(R.id.search_pic_iv);
            searchNameTv = itemView.findViewById(R.id.search_name_tv);
            searchProfessionTv = itemView.findViewById(R.id.search_profession_tv);
            searchFollowBtn = itemView.findViewById(R.id.search_follow_btn);

        }
    }
}
