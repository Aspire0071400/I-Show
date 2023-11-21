package com.aspire.ishow.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.CommentActivity;
import com.aspire.ishow.Model.FeedsModel;
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

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.viewholder>{

    ArrayList<FeedsModel> feedList;
    Context context;


    public FeedsAdapter(ArrayList<FeedsModel> feedList, Context context) {
        this.feedList = feedList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feeds_layout,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        FeedsModel  model = feedList.get(position);
        Picasso.get().load(model.getPostImage())
                .placeholder(R.drawable.profileplaceholder)
                .into(holder.feeds_post_iv);

        holder.feeds_like_tv.setText(model.getPostLike() + "");
        String description = model.getPostDescription();
        if(description.equals("")) {
            holder.feeds_description_tv.setVisibility(View.GONE);
        }else {
            holder.feeds_description_tv.setText(model.getPostDescription());
            holder.feeds_description_tv.setVisibility(View.VISIBLE  );
        }


        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(model.getPostedBy()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder).into(holder.feeds_image_dp);

                        holder.feeds_username.setText(user.getName());
                        holder.feeds_profession.setText(user.getProfession());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        FirebaseDatabase.getInstance().getReference().child("posts")
                        .child(model.getPostId()).child("likes")
                        .child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            holder.feeds_like_tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like,0,0,0);

                        }else{
                            holder.feeds_like_tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FirebaseDatabase.getInstance().getReference().child("posts")
                                            .child(model.getPostId()).child("likes")
                                            .child(FirebaseAuth.getInstance().getUid()).setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference().child("posts")
                                                            .child(model.getPostId()).child("postLike")
                                                            .setValue(model.getPostLike() + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    holder.feeds_like_tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like,0,0,0);
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
        holder.feeds_comment_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CommentActivity.class);
                i.putExtra("postId",model.getPostId());
                i.putExtra("postedBy",model.getPostedBy());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{

        ImageView feeds_image_dp,feeds_post_iv,feeds_savepost_iv,feeds_more_iv;
        TextView feeds_username, feeds_profession,feeds_like_tv,feeds_comment_tv,feeds_share_tv,feeds_description_tv;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            feeds_image_dp = itemView.findViewById(R.id.feeds_image_dp);
            feeds_post_iv = itemView.findViewById(R.id.feeds_post_iv);
            feeds_savepost_iv = itemView.findViewById(R.id.feeds_savepost_iv);
            feeds_more_iv = itemView.findViewById(R.id.feeds_more_iv);
            feeds_username = itemView.findViewById(R.id.feeds_username);
            feeds_profession = itemView.findViewById(R.id.feeds_profession);
            feeds_like_tv = itemView.findViewById(R.id.feeds_like_iv);
            feeds_comment_tv = itemView.findViewById(R.id.feeds_comment_iv);
            feeds_share_tv = itemView.findViewById(R.id.feeds_share_iv);
            feeds_description_tv = itemView.findViewById(R.id.feeds_description_tv);

        }
    }
}
