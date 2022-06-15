package com.example.chat_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import viewmodels.contact;
import viewmodels.contact_viewmodel;

import android.os.Bundle;

import java.util.Observer;

public class Converstaions_List extends AppCompatActivity {

    private contact_viewmodel viewmodel_contacts;

        @Override
     protected void onCreate(Bundle savedInstanceState) {
        viewmodel_contacts = new ViewModelProvider(this).get(contact_viewmodel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converstaions_list);

    }
}