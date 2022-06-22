package com.example.chat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.concurrent.atomic.AtomicReference;

import APIservice.WebService;


public class Login_Page extends AppCompatActivity {

    private WebService service;
    private final String TAG = "on clicl:";
    SharedPreferences sharedPreferences = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_Chat_android_Night); //when dark mode is enabled, we use the dark theme
        } else {
            setTheme(R.style.Theme_Chat_android_Night); //default app theme
        }

        service = new WebService();
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.login_buttton);
        TextView nickname = findViewById(R.id.nickname);
        TextView password = findViewById(R.id.login_password);
        Button registerLink = findViewById(R.id.register_link);
        final String[] newToken = new String[1];
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            newToken[0] = instanceIdResult.getToken();


        });

        login.setOnClickListener(v->{
            if (service.login(nickname.getText().toString(),password.getText().toString(), newToken[0], this)) {
               // Intent i = new Intent(this, Converstaions_List.class);
              //  startActivity(i);
            }

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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.darkMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               // switchCompat.setChecked(true);
                //imageView.setImageResource(R.drawable.night);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode",true);
                editor.commit();


                //Log.d(TAG, "onOptionsItemSelected: dark mode");
               // setTheme(R.style.Theme_Chat_android);
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                break;
            case R.id.brightMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
              //  switchCompat.setChecked(false);
               // imageView.setImageResource(R.drawable.night);
                SharedPreferences.Editor editorr = sharedPreferences.edit();
                editorr.putBoolean("night_mode",false);
                editorr.commit();

                //Log.d(TAG, "onOptionsItemSelected: bright mode");
                //setTheme(R.style.Theme_Chat_android_Night);
               // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default: return super.onOptionsItemSelected(item);

        }
        return true;
    }

}