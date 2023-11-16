package com.aspire.ishow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aspire.ishow.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;

    TextInputEditText register_name_tied,register_bio_tied,register_email_tied,register_pass_tied,register_about_tied;
    Button register_btn;
    TextView register_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        register_name_tied = findViewById(R.id.register_name_tied);
        register_pass_tied = findViewById(R.id.register_pass_tied);
        register_bio_tied = findViewById(R.id.register_bio_tied);
        register_email_tied = findViewById(R.id.register_email_tied);
        register_btn = findViewById(R.id.register_btn);
        register_to_login = findViewById(R.id.register_to_login);
        register_about_tied = findViewById(R.id.register_about_tied);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = register_email_tied.getText().toString(),
                        password = register_pass_tied.getText().toString(),
                        name = register_name_tied.getText().toString(),
                        profession = register_bio_tied.getText().toString(),
                        about = register_about_tied.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Fill Email",Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Fill Password",Toast.LENGTH_SHORT).show();
                } else if (about.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Fill About",Toast.LENGTH_SHORT).show();

                } else if (profession.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Fill Profession",Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Fill Name",Toast.LENGTH_SHORT).show();
                }else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(name, profession,about, email, password);
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);
                                Toast.makeText(RegisterActivity.this, "Registeration Successful", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Internal Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }

            }
        });

        register_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "So you Already have an Account!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}