package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Date;
import java.util.List;

import adapters.message_adapter;
import viewmodels.message;

public class Chat_Page extends AppCompatActivity {
    public List<message> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messages.add(new message(1,"hi", new Date(),true));
        messages.add(new message(2,"hello", new Date(),false));
        RecyclerView lstMessage = findViewById(R.id.messages);
        final message_adapter message_adapter = new message_adapter(this);
        lstMessage.setAdapter(message_adapter);
        lstMessage.setLayoutManager(new LinearLayoutManager(this));
        message_adapter.setMessages(messages);
        setContentView(R.layout.activity_chat_page);
    }
}