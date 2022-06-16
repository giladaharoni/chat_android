package com.example.chat_android;

import adapters.contacts_adapter;
import adapters.message_adapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import viewmodels.contact;
import viewmodels.contact_viewmodel;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;

public class Converstaions_List extends AppCompatActivity {

    //private contact_viewmodel viewmodel_contacts;
    private List<contact> contacts;
        @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //viewmodel_contacts = new ViewModelProvider(this).get(contact_viewmodel.class);
        this.contacts = new ArrayList<contact>();

        setContentView(R.layout.activity_converstaions_list);
        contacts.add(new contact("33", "dana", "2", "lalalalla", new Date()));
        contacts.add(new contact("34", "didi", "5", "kkkkk", new Date()));

            RecyclerView lstMessage = findViewById(R.id.contact_list);
            final contacts_adapter c_adapter = new contacts_adapter(this);
            lstMessage.setAdapter(c_adapter);
            lstMessage.setLayoutManager(new LinearLayoutManager(this));
            c_adapter.setContacts(contacts);
    }
}