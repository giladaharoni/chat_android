package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.concurrent.atomic.AtomicReference;

import APIservice.WebService;

public class Register_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Button register = findViewById(R.id.login_buttton);
        TextView regis_nickname = findViewById(R.id.regis_nickname);
        TextView email = findViewById(R.id.email);
        TextView loginPassword = findViewById(R.id.login_password);
        TextView confirm = findViewById(R.id.regis_confirm);
        final String[] newToken = new String[1];
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            newToken[0] = instanceIdResult.getToken();
        });
        WebService service = new WebService();
        register.setOnClickListener(v->{
            if (!confirm.getText().toString().equals(loginPassword.getText().toString())){
                return;
            }
            service.register(email.getText().toString(),regis_nickname.getText().toString(),loginPassword.getText().toString(),newToken[0],this);

            //validate all the parameters;
            //Intent i = new Intent(this, Converstaions_List.class);
           // startActivity(i);

        });
        Button switchLogin = findViewById(R.id.register_link);
        switchLogin.setOnClickListener(v->{
            Intent i = new Intent(this, Login_Page.class);
            startActivity(i);
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting,menu);
        return true;
    }
}