package APIservice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chat_android.Converstaions_List;
import com.example.chat_android.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import viewmodels.contact;


public class WebService {
    Retrofit retrofit;
    webApi webApi;
    SessionManager manager;

    public WebService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webApi = retrofit.create(webApi.class);
        manager = new SessionManager();
    }

    public void get(){
        Call<List<contact>> call = webApi.getContacts();
        call.enqueue(new Callback<List<contact>>() {
            @Override
            public void onResponse(Call<List<contact>> call, Response<List<contact>> response) {

                List<contact> contacts= response.body();
            }

            @Override
            public void onFailure(Call<List<contact>> call, Throwable t) {

            }
        });
    }

    public boolean login(String name, String password, Context login){
        // call -> async
        Call<Void> call = webApi.login(name, password);
        final boolean[] isLogin = {false};
        call.enqueue(new Callback<Void>() {
            private static final String TAG = "";

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //when the request http succeed
                Log.d(TAG, "onResponse: "+response.message());
                Intent i = new Intent(login, Converstaions_List.class);
                login.startActivity(i);
                isLogin[0] = true;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //when the request http failed
                Log.d(TAG, "onResponse: ");
                isLogin[0] = false;
            }
        });
        return isLogin[0];


    }

}
