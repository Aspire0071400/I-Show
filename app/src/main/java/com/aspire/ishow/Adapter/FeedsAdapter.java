package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.FeedsModel;
import com.aspire.ishow.R;

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
        holder.feeds_image_dp.setImageResource(model.getProfileDp());
        holder.feeds_username.setText(model.getUsername());
        holder.feeds_post_iv.setImageResource(model.getPostImage());
        holder.feeds_like_iv.setText(model.getLikes());
        holder.feeds_comment_iv.setText(model.getComment());
        holder.feeds_share_iv.setText(model.getShare());
        holder.feeds_profession.setText(model.getProfession());

    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView feeds_image_dp,feeds_post_iv,feeds_savepost_iv,feeds_more_iv;
        TextView feeds_username, feeds_profession,feeds_like_iv,feeds_comment_iv,feeds_share_iv;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            feeds_image_dp = itemView.findViewById(R.id.feeds_image_dp);
            feeds_post_iv = itemView.findViewById(R.id.feeds_post_iv);
            feeds_savepost_iv = itemView.findViewById(R.id.feeds_savepost_iv);
            feeds_more_iv = itemView.findViewById(R.id.feeds_more_iv);
            feeds_username = itemView.findViewById(R.id.feeds_username);
            feeds_profession = itemView.findViewById(R.id.feeds_profession);
            feeds_like_iv = itemView.findViewById(R.id.feeds_like_iv);
            feeds_comment_iv = itemView.findViewById(R.id.feeds_comment_iv);
            feeds_share_iv = itemView.findViewById(R.id.feeds_share_iv);

        }
    }
}
