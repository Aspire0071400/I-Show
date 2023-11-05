package com.aspire.ishow.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspire.ishow.Adapter.StoriesAdapter;
import com.aspire.ishow.Model.StoriesModel;
import com.aspire.ishow.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView stories_recycler;
    ArrayList<StoriesModel> storyList;
    public HomeFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        stories_recycler = view.findViewById(R.id.stories_recycler);

        storyList = new ArrayList<>();
        storyList.add(new StoriesModel(R.drawable.story,R.drawable.live2,R.drawable.usericon,"varun"));
        storyList.add(new StoriesModel(R.drawable.story,R.drawable.live2,R.drawable.usericon,"varun"));
        storyList.add(new StoriesModel(R.drawable.story,R.drawable.live2,R.drawable.usericon,"varun"));
        storyList.add(new StoriesModel(R.drawable.story,R.drawable.live2,R.drawable.usericon,"varun"));

        StoriesAdapter adapter = new StoriesAdapter(storyList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        stories_recycler.setLayoutManager(linearLayoutManager);
        stories_recycler.setNestedScrollingEnabled(false);
        stories_recycler.setAdapter(adapter);

        return view;
    }
}