package com.aspire.ishow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aspire.ishow.Model.CommentsModel;
import com.aspire.ishow.Model.FeedsModel;
import com.aspire.ishow.Model.User;
import com.aspire.ishow.databinding.ActivityCommentBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    ActivityCommentBinding binding;
//    TextView comments_username_tv,comments_like_tv,comments_comment_tv,comments_share_tv,comments_description_tv;
//    ImageView comments_post_iv,comments_dp_iv,comments_send_iv;
//    EditText comments_edt;
    Intent i;
    String postId,postedBy;
    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        i = getIntent();
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        postId = i.getStringExtra("postId");
        postedBy = i.getStringExtra("postedBy");


        db.getReference().child("posts")
                .child(postId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        FeedsModel post = snapshot.getValue(FeedsModel.class);
                        assert post != null;
                        Picasso.get()
                                .load(post.getPostImage())
                                .placeholder(R.drawable.coverplaceholder)
                                .into(binding.commentsPostIv);
                        binding.commentsDescriptionTv.setText(post.getPostDescription());
                        binding.commentsLikeTv.setText(post.getPostLike()+"");
                        binding.commentsCommentTv.setText(post.getCommentCount()+"");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        db.getReference().child("Users")
                .child(postedBy).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        Picasso.get().load(user.getProfile())
                                .placeholder(R.drawable.profileplaceholder)
                                .into(binding.commentsDpIv);

                        binding.commentsUsernameTv.setText(user.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        binding.commentsSendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentsModel commentsModel = new CommentsModel();
                commentsModel.setComment(binding.commentsEdt.getText().toString());
                commentsModel.setCommentedAt(new Date().getTime());
                commentsModel.setCommentedBy(FirebaseAuth.getInstance().getUid());

                db.getReference().child("posts")
                        .child(postId).child("comments")
                        .push()
                        .setValue(commentsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                db.getReference().child("posts").child(postId)
                                        .child("commentCount")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int commentCount = 0;
                                                if(snapshot.exists()){
                                                    commentCount = snapshot.getValue(Integer.class);
                                                }
                                                db.getReference().child("posts").child(postId)
                                                        .child("commentCount").setValue(commentCount + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                binding.commentsEdt.setText("");
                                                                Toast.makeText(CommentActivity.this, "Commented", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                            }
                        });
            }
        });


    }
}