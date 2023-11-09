package com.aspire.ishow.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.NotificationModel;
import com.aspire.ishow.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder>{
    ArrayList<NotificationModel> notifyList;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> notifyList, Context context) {
        this.notifyList = notifyList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_switch_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationModel model4 = notifyList.get(position);
        holder.notificationDP.setImageResource(model4.getProfileImg());
        holder.notificationMentionTv.setText(Html.fromHtml(model4.getNotify()));
        holder.notificationTimeTv.setText(model4.getTime());

    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView notificationDP;
        TextView notificationMentionTv,notificationTimeTv;
        public viewHolder(@NonNull View itemView) {
        super(itemView);
            notificationDP = itemView.findViewById(R.id.notification_dp);
            notificationMentionTv = itemView.findViewById(R.id.notification_mention_tv);
            notificationTimeTv = itemView.findViewById(R.id.notification_time_tv);


        }
    }
}
