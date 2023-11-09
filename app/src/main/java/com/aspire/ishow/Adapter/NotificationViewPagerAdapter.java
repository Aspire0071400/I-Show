package com.aspire.ishow.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.aspire.ishow.Fragments.NotificationSwitchFragment;
import com.aspire.ishow.Fragments.RequestSwitchFragment;

public class NotificationViewPagerAdapter extends FragmentPagerAdapter {
    public NotificationViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:return new RequestSwitchFragment();
            case 0:
            default:return new NotificationSwitchFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = "NOTIFICATION";
        }else if (position == 1){
            title = "REQUESTS";
        }

        return title;
    }
}
