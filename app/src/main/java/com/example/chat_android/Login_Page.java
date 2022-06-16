package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Login_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.login_buttton);
        TextView nickname = findViewById(R.id.nickname);
        TextView password = findViewById(R.id.login_password);
        Button registerLink = findViewById(R.id.register_link);

        login.setOnClickListener(v->{
            Intent i = new Intent(this, Converstaions_List.class);
            startActivity(i);

        });
        registerLink.setOnClickListener(v->{
            Intent i = new Intent(this, Register_Page.class);
            startActivity(i);
        });


    }
}