package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;

import APIservice.WebService;

public class Login_Page extends AppCompatActivity {

    private WebService service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new WebService();
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.login_buttton);
        TextView nickname = findViewById(R.id.nickname);
        TextView password = findViewById(R.id.login_password);
        Button registerLink = findViewById(R.id.register_link);

        login.setOnClickListener(v->{
            //if (service.login("do","1", this)) {
                Intent i = new Intent(this, Converstaions_List.class);
                startActivity(i);
            //}

        });
        registerLink.setOnClickListener(v->{
            Intent i = new Intent(this, Register_Page.class);
            startActivity(i);
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting,menu);
        return true;
    }
}