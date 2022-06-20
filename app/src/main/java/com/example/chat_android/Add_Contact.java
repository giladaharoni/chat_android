package com.example.chat_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import APIservice.MyApplication;
import DAO.AppDb;
import DAO.contactDao;
import viewmodels.contact;

public class Add_Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Button add_contact = findViewById(R.id.add_contact_btn);
        TextView contact_form = findViewById(R.id.addContact_nickname);
        add_contact.setOnClickListener(v ->{
            //call from the server - later. get the data about him.
            contact c = new contact("0", (String) contact_form.getText(),"","",null);
            AppDb db = Room.databaseBuilder(MyApplication.context,AppDb.class,"db").build();
            contactDao dao = db.contactDao();
            dao.insert(c);
            Intent i = new Intent(this, Converstaions_List.class);
            startActivity(i);
        });
    }

}