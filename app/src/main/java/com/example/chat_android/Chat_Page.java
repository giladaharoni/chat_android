package com.example.chat_android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        Intent i = getIntent();
        String id = (String) i.getExtras().get("id");
        String cname = (String) i.getExtras().get("name");
        String lastdate = (String) i.getExtras().get("last_date");
        String pattern = " HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        contact = new contact(id,cname,"","", "");
        Context t = this;



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

        MyService.notifi.set_obj(new Notification_recieved_listener.on_notification_recieved() {
            @Override
            public void func_recieved() {
                service.getMessage(message_adapter,id);
//                Intent j = new Intent(t, Chat_Page.class);
//                j.putExtra("id", id);
//                j.putExtra("name", cname);
//                j.putExtra("last_date", lastdate);
//                startActivity(j);
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