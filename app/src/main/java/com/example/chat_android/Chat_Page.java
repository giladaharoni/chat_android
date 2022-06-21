package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapters.message_adapter;
import viewmodels.contact;
import viewmodels.message;

public class Chat_Page extends AppCompatActivity {
    private static final String TAG = "";
    public List<message> messages;
    private viewmodels.contact contact;
    message_adapter message_adapter;
    EditText sendBox;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        contact = new contact((String) i.getExtras().get("id"),(String) i.getExtras().get("name"),"","", (String)i.getExtras().get("last_date"));
        setContentView(R.layout.activity_chat_page);
        messages = new ArrayList<>();
        messages.add(new message(1,"hi", new Date(),true));
        messages.add(new message(2,"hello", new Date(),false));
        messages.add(new message(3,"hesd", new Date(),false));
        TextView name = findViewById(R.id.Name_contact_chat_page);
        name.setText(contact.getName());
        TextView date = findViewById(R.id.lastseen_chat_page);
        date.setText(contact.getLastDate());
        RecyclerView lstMessage = findViewById(R.id.messages);
        message_adapter message_adapter = new message_adapter(this);
        lstMessage.setAdapter(message_adapter);
        lstMessage.setLayoutManager(new LinearLayoutManager(this));
        message_adapter.setMessages(messages);
        send = findViewById(R.id.sendButton);
        sendBox = findViewById(R.id.messageToSend);
        send.setOnClickListener(v->{
            if (sendBox.getText().toString().equals("")){
            } else {
                String message = sendBox.getText().toString();
                messages.add(new message(3,message,new Date(),true));
                message_adapter.notifyDataSetChanged();
                Log.d(TAG, "onCreate: "+message);
                sendBox.setText("");

            }
        });


    }


}