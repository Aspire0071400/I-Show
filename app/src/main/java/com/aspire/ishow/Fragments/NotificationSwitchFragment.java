package com.aspire.ishow.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspire.ishow.Adapter.NotificationAdapter;
import com.aspire.ishow.Model.NotificationModel;
import com.aspire.ishow.R;

import java.util.ArrayList;

public class NotificationSwitchFragment extends Fragment {
    RecyclerView notificationSwitchRv;
    ArrayList<NotificationModel> notifyList;
    public NotificationSwitchFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_switch, container, false);
        notificationSwitchRv = view.findViewById(R.id.notification_switch_rv);

        notifyList = new ArrayList<>();
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> Mentioned you in a comment","just now"));
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> Mentioned you in a comment","just now"));
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> Mentioned you in a comment","just now"));
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> commented on your Post","48 Minutes"));
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> Mentioned you in a comment","just now"));
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> Mentioned you in a comment","1hr"));
        notifyList.add(new NotificationModel(R.drawable.usericon,"<b>Aspire</b> Mentioned you in a comment","just now"));


        NotificationAdapter adapter = new NotificationAdapter(notifyList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        notificationSwitchRv.setLayoutManager(linearLayoutManager);
        notificationSwitchRv.setAdapter(adapter);

        return view;
    }
}