package com.example.chat_android;

import APIservice.WebService;
import adapters.contacts_adapter;
import adapters.message_adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import viewmodels.contact;
//import viewmodels.contact_viewmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
    SharedPreferences sharedPreferences = null;


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