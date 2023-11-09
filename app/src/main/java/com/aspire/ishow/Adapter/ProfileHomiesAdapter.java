package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.ProfileHomiesModel;
import com.aspire.ishow.R;

import java.util.ArrayList;

public class ProfileHomiesAdapter extends RecyclerView.Adapter<ProfileHomiesAdapter.viewHolder> {

    ArrayList<ProfileHomiesModel> homieList;
    Context context;

    public ProfileHomiesAdapter(ArrayList<ProfileHomiesModel> homieList, Context context) {
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
        ProfileHomiesModel model3  = homieList.get(position);
        holder.profileHomieDpIv.setImageResource(model3.getProfileHomieDp());

    }

    @Override
    public int getItemCount() {
        return homieList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profileHomieDpIv;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profileHomieDpIv = itemView.findViewById(R.id.profile_homie_dp_iv);
        }
    }
}
