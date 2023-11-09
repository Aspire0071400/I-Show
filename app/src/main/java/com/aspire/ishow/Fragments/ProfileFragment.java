package com.aspire.ishow.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspire.ishow.Adapter.ProfileHomiesAdapter;
import com.aspire.ishow.Model.ProfileHomiesModel;
import com.aspire.ishow.R;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    ArrayList<ProfileHomiesModel> homieList;
    RecyclerView profileHomiesRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileHomiesRv = view.findViewById(R.id.profile_homies_rv);

        homieList = new ArrayList<>();

        homieList.add(new ProfileHomiesModel(R.drawable.friends));
        homieList.add(new ProfileHomiesModel(R.drawable.friends));
        homieList.add(new ProfileHomiesModel(R.drawable.friends));
        homieList.add(new ProfileHomiesModel(R.drawable.friends));
        homieList.add(new ProfileHomiesModel(R.drawable.friends));

        ProfileHomiesAdapter adapter3 = new ProfileHomiesAdapter(homieList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        profileHomiesRv.setLayoutManager(linearLayoutManager);
        profileHomiesRv.setAdapter(adapter3);

        return view;
    }
}