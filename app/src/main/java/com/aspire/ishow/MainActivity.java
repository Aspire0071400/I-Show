package com.aspire.ishow;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aspire.ishow.databinding.ActivityMainBinding;
import com.aspire.ishow.Fragments.HomeFragment;
import com.aspire.ishow.Fragments.NotificationFragment;
import com.aspire.ishow.Fragments.ProfileFragment;
import com.aspire.ishow.Fragments.SearchFragment;
import com.aspire.ishow.Fragments.UploadFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        MainActivity.this.setSupportActionBar(binding.toolbar);



        loadFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.home){
                loadFragment(new HomeFragment());
                MainActivity.this.setTitle("I Show");
                //binding.toolbar.setVisibility(View.GONE);
            } else if (item.getItemId() == R.id.notification) {
                loadFragment(new NotificationFragment());
                MainActivity.this.setTitle("Notifications");
                //binding.toolbar.setVisibility(View.GONE);
            } else if (item.getItemId() == R.id.search) {
                loadFragment(new SearchFragment());
                MainActivity.this.setTitle("Search");
                //binding.toolbar.setVisibility(View.GONE);
            } else if (item.getItemId() == R.id.profile) {
                loadFragment(new ProfileFragment());
                MainActivity.this.setTitle("My Profile");

            } else if (item.getItemId() == R.id.upload) {
                loadFragment(new UploadFragment());
                MainActivity.this.setTitle("Upload");
                //binding.toolbar.setVisibility(View.GONE);
            }
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_settings_layout,menu);
        return true;
    }

    private  void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout,fragment);
        ft.commit();
    }
}