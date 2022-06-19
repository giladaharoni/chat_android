package APIservice;

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

    public WebService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webApi = retrofit.create(webApi.class);
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

    public void login(String name,String password){
        webApi.login(name, password);

    }

}
