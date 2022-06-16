package com.example.chat_android;

import adapters.contacts_adapter;
import adapters.message_adapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import viewmodels.contact;
//import viewmodels.contact_viewmodel;

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
        setContentView(R.layout.activity_converstaions_list);

        //viewmodel_contacts = new ViewModelProvider(this).get(contact_viewmodel.class);
        contacts = new ArrayList<contact>();
        contacts.add(new contact("33", "dana", "2", "lalalalla", new Date()));
        contacts.add(new contact("34", "didi", "5", "kkkkk", new Date()));

        RecyclerView lstContact = findViewById(R.id.contacts);
        final contacts_adapter contacts_adapter = new contacts_adapter(this);
        lstContact.setAdapter(contacts_adapter);
        lstContact.setLayoutManager(new LinearLayoutManager(this));
        contacts_adapter.setContacts(contacts);
    }
}