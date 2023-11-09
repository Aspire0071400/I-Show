package com.aspire.ishow.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspire.ishow.Adapter.NotificationViewPagerAdapter;
import com.aspire.ishow.R;
import com.google.android.material.tabs.TabLayout;


public class NotificationFragment extends Fragment {

    ViewPager notificationViewPager;
    TabLayout notificationTabLayout;
    public NotificationFragment(){
        // Required Constructor.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        notificationTabLayout = view.findViewById(R.id.notification_tab_layout);
        notificationViewPager = view.findViewById(R.id.notification_view_pager);

        notificationViewPager.setAdapter(new NotificationViewPagerAdapter(getFragmentManager()));

        notificationTabLayout.setupWithViewPager(notificationViewPager);

        return view;
    }
}