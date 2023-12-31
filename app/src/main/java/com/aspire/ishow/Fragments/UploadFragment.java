package com.aspire.ishow.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.aspire.ishow.Model.FeedsModel;
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

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


public class UploadFragment extends Fragment {

    TextView upload_name_tv,upload_profession_tv;
    CircleImageView upload_pic_iv;
    ImageView upload_image_iv,upload_selector_iv;
    EditText upload_description_edt;
    Button upload_btn;
    Uri uri;
    FirebaseAuth auth;
    FirebaseDatabase db;
    FirebaseStorage storage;
    ProgressDialog load;

    public UploadFragment(){
    //Default constructor.
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        load = new ProgressDialog(getContext());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        load.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        load.setTitle("Posting");
        load.setMessage("Please Wait....");
        load.setCancelable(false);
        load.setCanceledOnTouchOutside(false);

        upload_pic_iv = view.findViewById(R.id.upload_pic_iv);
        upload_name_tv = view.findViewById(R.id.upload_name_tv);
        upload_profession_tv = view.findViewById(R.id.upload_profession_tv);
        upload_image_iv = view.findViewById(R.id.upload_image_iv);
        upload_selector_iv = view.findViewById(R.id.upload_selector_iv);
        upload_description_edt = view.findViewById(R.id.upload_description_edt);
        upload_btn = view.findViewById(R.id.upload_btn);

        db.getReference().child("Users")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            User user = snapshot.getValue(User.class);
                            Picasso.get().load(user.getProfile()).placeholder(R.drawable.profileplaceholder)
                                    .into(upload_pic_iv);
                            upload_name_tv.setText(user.getName());
                            upload_profession_tv.setText(user.getProfession());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        upload_description_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String description = upload_description_edt.getText().toString();
                if(!description.isEmpty()){
                    upload_btn.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.follow_btn_bg));
                    upload_btn.setTextColor(getContext().getResources().getColor(R.color.white));
                    upload_btn.setEnabled(true);
                }else {
                    upload_btn.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.following_btn_bg));
                    upload_btn.setTextColor(getContext().getResources().getColor(R.color.grey));
                    upload_btn.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load.show();
                final StorageReference reference = storage.getReference().child("posts")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child(new Date().getTime()+"");

                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                FeedsModel feed = new FeedsModel();
                                feed.setPostImage(uri.toString());
                                feed.setPostedBy(FirebaseAuth.getInstance().getUid());
                                feed.setPostDescription(upload_description_edt.getText().toString());
                                feed.setPostedAt(new Date().getTime());

                                db.getReference().child("posts")
                                        .push()
                                        .setValue(feed).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                load.dismiss();
                                                Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });
            }
        });

        upload_selector_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,10);


            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            uri = data.getData();
            upload_image_iv.setImageURI(uri);
            upload_image_iv.setVisibility(getView().VISIBLE);

            upload_btn.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.follow_btn_bg));
            upload_btn.setTextColor(getContext().getResources().getColor(R.color.white));
            upload_btn.setEnabled(true);


        }
    }
}