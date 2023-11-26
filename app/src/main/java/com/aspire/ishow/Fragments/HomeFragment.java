package com.aspire.ishow.Fragments;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Adapter.FeedsAdapter;
import com.aspire.ishow.Adapter.StoriesAdapter;
import com.aspire.ishow.Model.FeedsModel;
import com.aspire.ishow.Model.StoriesModel;
import com.aspire.ishow.Model.StoryViewModel;
import com.aspire.ishow.R;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment {
    RecyclerView stories_recycler;
    ShimmerRecyclerView feeds_recycler;
    RoundedImageView imageViewAddStory;
    ArrayList<StoriesModel> storyList;
    ArrayList<FeedsModel> feedsList;
    ActivityResultLauncher<String> galleryLauncher;
    FirebaseAuth auth;
    FirebaseDatabase db;
    FirebaseStorage storage;
    ProgressDialog loading;
    public HomeFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        feeds_recycler = view.findViewById(R.id.feeds_recycler);
        feeds_recycler.showShimmerAdapter();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setTitle("Story Uploading");
        loading.setMessage("Please Wait");
        loading.setCancelable(false);


//Story Setup

        stories_recycler = view.findViewById(R.id.stories_recycler);
        storyList = new ArrayList<>();

        StoriesAdapter adapter = new StoriesAdapter(storyList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        stories_recycler.setLayoutManager(linearLayoutManager);
        stories_recycler.setNestedScrollingEnabled(false);
        stories_recycler.setAdapter(adapter);

        db.getReference().child("stories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    storyList.clear();
                    for(DataSnapshot storySnapshot : snapshot.getChildren()){
                        StoriesModel storiesModel = new StoriesModel();
                        storiesModel.setStoryBy(storySnapshot.getKey());
                        storiesModel.setStoryAt(storySnapshot.child("postedBy").getValue(Long.class));

                        ArrayList<StoryViewModel> storyViewModels = new ArrayList<>();
                        for (DataSnapshot snapshot1 : storySnapshot.child("userStories").getChildren()){
                            StoryViewModel storyViewModel1 = snapshot1.getValue(StoryViewModel.class);
                            storyViewModels.add(storyViewModel1);
                        }

                        storiesModel.setStories(storyViewModels);
                        storyList.add(storiesModel);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//Feeds setup

        feedsList = new ArrayList<>();

        FeedsAdapter adapter1 = new FeedsAdapter(feedsList,getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        feeds_recycler.setLayoutManager(linearLayoutManager1);
        feeds_recycler.setNestedScrollingEnabled(false);


        db.getReference().child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feedsList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    FeedsModel feed = snapshot1.getValue(FeedsModel.class);
                    feed.setPostId(snapshot1.getKey());
                    feedsList.add(feed);
                }
                feeds_recycler.setAdapter(adapter1);
                feeds_recycler.hideShimmerAdapter();
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageViewAddStory = view.findViewById(R.id.imageViewAddStory);
        imageViewAddStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryLauncher.launch("image/*");
            }
        });

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri o) {
                        imageViewAddStory.setImageURI(o);
                        loading.show();

                        final StorageReference reference = storage.getReference().child("stories")
                                .child(FirebaseAuth.getInstance().getUid())
                                .child(new Date().getTime()+"");

                        reference.putFile(o).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        StoriesModel storiesModel = new StoriesModel();
                                        storiesModel.setStoryAt(new Date().getTime());
                                        db.getReference()
                                                .child("stories")
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .child("postedBy")
                                                .setValue(storiesModel.getStoryAt()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        StoryViewModel storyViewModel = new StoryViewModel(uri.toString(), storiesModel.getStoryAt());
                                                        db.getReference().child("stories")
                                                                .child(FirebaseAuth.getInstance().getUid())
                                                                .child("userStories")
                                                                .push().setValue(storyViewModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        loading.dismiss();
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                            }
                        });

                    }
                });

        return view;
    }
}