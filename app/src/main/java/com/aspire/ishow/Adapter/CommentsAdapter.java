package com.aspire.ishow.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.CommentsModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.R;
import com.aspire.ishow.databinding.CommentsLayoutBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.viewHolder>{
    Context context;
    ArrayList<CommentsModel> list;

    public CommentsAdapter(Context context, ArrayList<CommentsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CommentsModel commentsModel = list.get(position);
        //holder.binding.commentTv.setText(commentsModel.getComment());
        String time = TimeAgo.using(commentsModel.getCommentedAt());
        holder.binding.commentTimeTv.setText(time);

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(commentsModel.getCommentedBy()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder).into(holder.binding.commentDpIv);
                        holder.binding.commentTv.setText(Html.fromHtml("<b>"+user.getName()+"</b>" +"  "+commentsModel.getComment()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CommentsLayoutBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CommentsLayoutBinding.bind(itemView);
        }
    }
}
