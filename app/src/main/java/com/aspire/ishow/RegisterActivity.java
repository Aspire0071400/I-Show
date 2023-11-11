package com.aspire.ishow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText register_name_tied,register_bio_tied,register_email_tied,register_pass_tied;
    Button register_btn;
    TextView register_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name_tied = findViewById(R.id.register_name_tied);
        register_pass_tied = findViewById(R.id.register_pass_tied);
        register_bio_tied = findViewById(R.id.register_bio_tied);
        register_email_tied = findViewById(R.id.register_email_tied);
        register_btn = findViewById(R.id.register_btn);
        register_to_login = findViewById(R.id.register_to_login);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Registeration Successful", Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
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