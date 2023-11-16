package com.aspire.ishow.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Adapter.FeedsAdapter;
import com.aspire.ishow.Adapter.StoriesAdapter;
import com.aspire.ishow.Model.FeedsModel;
import com.aspire.ishow.Model.StoriesModel;
import com.aspire.ishow.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView stories_recycler,feeds_recycler;
    ArrayList<StoriesModel> storyList;
    ArrayList<FeedsModel> feedsList;
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

        feeds_recycler = view.findViewById(R.id.feeds_recycler);
        feedsList = new ArrayList<>();
        feedsList.add(new FeedsModel(R.drawable.stories,R.drawable.story,R.drawable.outline_bookmark_border_24,"Varun","App Dev","100","50","35"));
        feedsList.add(new FeedsModel(R.drawable.stories,R.drawable.story,R.drawable.outline_bookmark_border_24,"Varun","App Dev","100","50","35"));
        feedsList.add(new FeedsModel(R.drawable.stories,R.drawable.story,R.drawable.outline_bookmark_border_24,"Varun","App Dev","100","50","35"));
        feedsList.add(new FeedsModel(R.drawable.stories,R.drawable.story,R.drawable.outline_bookmark_border_24,"Varun","App Dev","100","50","35"));
        feedsList.add(new FeedsModel(R.drawable.stories,R.drawable.story,R.drawable.outline_bookmark_border_24,"Varun","App Dev","100","50","35"));
        feedsList.add(new FeedsModel(R.drawable.stories,R.drawable.story,R.drawable.outline_bookmark_border_24,"Varun","App Dev","100","50","35"));

        FeedsAdapter adapter1 = new FeedsAdapter(feedsList,getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        feeds_recycler.setLayoutManager(linearLayoutManager1);
        feeds_recycler.setNestedScrollingEnabled(false);
        feeds_recycler.setAdapter(adapter1);

        return view;
    }
}