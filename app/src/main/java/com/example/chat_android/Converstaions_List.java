package com.example.chat_android;

import APIservice.WebService;
import adapters.contacts_adapter;
import adapters.message_adapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import viewmodels.contact;
//import viewmodels.contact_viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;

public class Converstaions_List extends AppCompatActivity implements contacts_adapter.OnContactClickListener {
    private  contacts_adapter contacts_adapter;
    private static final String TAG = "conv:";
    //private contact_viewmodel viewmodel_contacts;
    private List<contact> contacts;
    private FloatingActionButton addContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converstaions_list);
        WebService service = new WebService();
        addContact = findViewById(R.id.add_contact_btn);
        addContact.setOnClickListener(v->{
            Intent i = new Intent(this,Add_Contact.class);
            startActivity(i);
        });
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.d(TAG, "the token is  "+newToken);
        });


        //viewmodel_contacts = new ViewModelProvider(this).get(contact_viewmodel.class);

      //  contacts = new ArrayList<contact>();
       // contacts.add(new contact("33", "dana", "2", "lalalalla", new Date()));
       // contacts.add(new contact("34", "didi", "5", "kkkkk", new Date()));

        RecyclerView lstContact = findViewById(R.id.contacts);
        contacts_adapter = new contacts_adapter(this,this);
        lstContact.setAdapter(contacts_adapter);
        lstContact.setLayoutManager(new LinearLayoutManager(this));
        //contacts_adapter.setContacts(contacts);
        service.getContacts(contacts_adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        WebService service = new WebService();
        service.getContacts(contacts_adapter);

    }

    @Override
    public void onContactClick(int position) {
        //WebService service = new WebService();
       // service.get();

        Intent intent = new Intent(this,Chat_Page.class);
        contact con = contacts_adapter.getContacts().get(position);
        //Log.d(TAG, "onContactClick: "+ID);
        intent.putExtra("id",con.getId());
        intent.putExtra("name",con.getName());
        intent.putExtra("last_date",con.getLastDate());
        startActivity(intent);
    }
}