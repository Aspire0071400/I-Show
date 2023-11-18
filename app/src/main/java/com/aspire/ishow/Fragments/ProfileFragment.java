package com.aspire.ishow.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspire.ishow.Adapter.HomiesAdapter;
import com.aspire.ishow.Model.HomiesModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase db;
    ArrayList<HomiesModel> homieList;
    RecyclerView profileHomiesRv;
    TextView profile_name_tv, profile_profession_tv,profile_about_tv,profile_followers_tv,profile_homies_tv,profile_photos_tv;
    ImageView profile_cover_upload_iv,profile_cover_iv,profile_pic_iv,profile_pic_upload_iv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profile_cover_upload_iv = view.findViewById(R.id.profile_cover_upload_iv);
        profile_cover_iv = view.findViewById(R.id.profile_cover_iv);
        profileHomiesRv = view.findViewById(R.id.profile_homies_rv);
        profile_name_tv = view.findViewById(R.id.profile_name_tv);
        profile_profession_tv = view.findViewById(R.id.profile_profession_tv);
        profile_about_tv = view.findViewById(R.id.profile_about_tv);
        profile_pic_iv = view.findViewById(R.id.profile_pic_iv);
        profile_pic_upload_iv = view.findViewById(R.id.profile_pic_upload_iv);
        profile_followers_tv = view.findViewById(R.id.profile_followers_tv);
        profile_homies_tv = view.findViewById(R.id.profile_homies_tv);
        profile_photos_tv = view.findViewById(R.id.profile_photos_tv);

        db.getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    Picasso.get().load(user.getCoverPhoto()).placeholder(R.drawable.coverplaceholder).into(profile_cover_iv);
                    Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder).into(profile_pic_iv);

                    profile_name_tv.setText(user.getName());
                    profile_profession_tv.setText(user.getProfession());
                    profile_about_tv.setText(user.getAbout());

                    profile_followers_tv.setText(user.getFollowerCount()+"");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        homieList = new ArrayList<>();


        HomiesAdapter adapter3 = new HomiesAdapter(homieList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        profileHomiesRv.setLayoutManager(linearLayoutManager);
        profileHomiesRv.setAdapter(adapter3);

        db.getReference().child("Users")
                        .child(auth.getUid())
                                .child("followers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        homieList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            HomiesModel homiesModel = dataSnapshot.getValue(HomiesModel.class);
                            homieList.add(homiesModel);

                        }
                        adapter3.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        profile_cover_upload_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(i.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,11);
            }
        });
        profile_pic_upload_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(i.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,22);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 11){
            if(data.getData() != null){
                Uri uri = data.getData();
                profile_cover_iv.setImageURI(uri);

                final StorageReference ref = storage.getReference().child("cover_photo").child(FirebaseAuth.getInstance().getUid());
                ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(),"Cover Image Uploaded",Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                db.getReference().child("Users").child(auth.getUid()).child("coverPhoto").setValue(uri.toString());

                            }
                        });
                    }
                });
            }
        }
        if(requestCode == 22){
            if(data.getData() != null){
                Uri uri = data.getData();
                profile_pic_iv.setImageURI(uri);

                final StorageReference ref = storage.getReference().child("profile_image").child(FirebaseAuth.getInstance().getUid());
                ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(),"Profile Image Uploaded",Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                db.getReference().child("Users").child(auth.getUid()).child("profile").setValue(uri.toString());

                            }
                        });
                    }
                });
            }
        }


    }
}