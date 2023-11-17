package com.aspire.ishow.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.HomiesModel;
import com.aspire.ishow.R;

import java.util.ArrayList;

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


    }

    @Override
    public int getItemCount() {
        return homieList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{


        public viewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
