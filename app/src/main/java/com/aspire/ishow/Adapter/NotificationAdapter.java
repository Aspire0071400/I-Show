package com.aspire.ishow.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Model.NotificationModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        View view = LayoutInflater.from(context).inflate(R.layout.notification_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationModel model = notifyList.get(position);
        String type = model.getType();

        FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(model.getNotificationBy()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder).into(holder.notificatioDp);

                        if (type.equals("like")){
                            holder.notificationMentionTv.setText(Html.fromHtml("<b>" + user.getName() + "</b>" + " Liked your Post"));
                        } else if (type.equals("comment")) {
                            holder.notificationMentionTv.setText(Html.fromHtml("<b>" + user.getName() + "</b>" + " Commented on your post"));
                        }else if(type.equals("follow")){
                            holder.notificationMentionTv.setText(Html.fromHtml("<b>" + user.getName() + "</b>" + " Started Following you"));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        /*holder.openNotifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!type.equals("follow")) {
                        holder.openNotifier.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                        Intent i = new Intent(context, CommentActivity.class);
                        i.putExtra("postId", model.getPostId());
                        i.putExtra("postedBy", model.getPostedBy());
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }

            }
        });*/
        Boolean checkOpen = model.isCkeckOpen();
        if (checkOpen == true){
            holder.openNotifier.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }else { }
    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView notificatioDp;
        TextView notificationMentionTv,notificationTime;
        ConstraintLayout openNotifier;
        public viewHolder(@NonNull View itemView) {
        super(itemView);
        notificatioDp = itemView.findViewById(R.id.notification_dp);
        notificationMentionTv = itemView.findViewById(R.id.notification_mention_tv);
        notificationTime = itemView.findViewById(R.id.notification_time_tv);
        openNotifier = itemView.findViewById(R.id.openNotifier);


        }
    }
}
