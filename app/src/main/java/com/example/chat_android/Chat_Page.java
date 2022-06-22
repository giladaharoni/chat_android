package com.example.chat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import APIservice.WebService;
import adapters.contacts_adapter;
import adapters.message_adapter;
import viewmodels.contact;
import viewmodels.message;

public class Chat_Page extends AppCompatActivity {
    private static final String TAG = "";

    private viewmodels.contact contact;
    message_adapter message_adapter;
    EditText sendBox;
    Button send;
    SharedPreferences sharedPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        Intent i = getIntent();
        String id = (String) i.getExtras().get("id");
        String cname = (String) i.getExtras().get("name");
        String lastdate = (String) i.getExtras().get("last_date");
        contact = new contact(id,cname,"","", lastdate);

        RecyclerView lstContact = findViewById(R.id.messages);
        WebService service = new WebService();

        message_adapter = new message_adapter(this);
        lstContact.setAdapter(message_adapter);
        lstContact.setLayoutManager(new LinearLayoutManager(this));
        //contacts_adapter.setContacts(contacts);
        service.getMessage(message_adapter,id);

        TextView name = findViewById(R.id.Name_contact_chat_page);
        name.setText(contact.getName());
        TextView date = findViewById(R.id.lastseen_chat_page);
        date.setText(contact.getLastDate());
        RecyclerView lstMessage = findViewById(R.id.messages);
        lstMessage.setAdapter(message_adapter);
        lstMessage.setLayoutManager(new LinearLayoutManager(this));
        send = findViewById(R.id.sendButton);
        sendBox = findViewById(R.id.messageToSend);
        send.setOnClickListener(v->{
            if (sendBox.getText().toString().equals("")){
            } else {
                String message = sendBox.getText().toString();
                service.postMessage(message,id, message_adapter);

                Log.d(TAG, "onCreate: "+message);
                sendBox.setText("");

            }
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