package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Register_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Button register = findViewById(R.id.login_buttton);
        register.setOnClickListener(v->{
            //validate all the parameters;
            Intent i = new Intent(this, Converstaions_List.class);
            startActivity(i);
        });
        Button switchLogin = findViewById(R.id.register_link);
        switchLogin.setOnClickListener(v->{
            Intent i = new Intent(this, Login_Page.class);
            startActivity(i);
        });
    }
}