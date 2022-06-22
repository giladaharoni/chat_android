package APIservice;

import static APIservice.MyApplication.context;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.chat_android.Converstaions_List;
import com.example.chat_android.R;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

import adapters.contacts_adapter;
import adapters.message_adapter;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.JavaNetCookieJar;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import viewmodels.contact;
import viewmodels.message;


public class WebService {
    static Retrofit retrofit;
    static webApi webApi;
    SessionManager manager;

    public WebService() {
        manager = new SessionManager();
        if(retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.getString(R.string.base_url))
                    .client(new OkHttpClient.Builder().addInterceptor(logging).build())
                    .build();
            webApi = retrofit.create(APIservice.webApi.class);
        }


//        CookieManager cookieHandler = new CookieManager();
//        cookieHandler.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieHandler));
//// add your other interceptors …
//
//// add logging as last interceptor
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                okhttp3.Response response = chain.proceed(original);
//                String authToken = "Bearer " + manager.fetchAuthToken();
//
//
//                Request request = original.newBuilder()
//                        .header("Authorization", authToken)
//                        .method(original.method(), original.body()).build();
//
//                return chain.proceed(request);
//            }
//        }).addInterceptor(logging);  // <-- this is the important line!
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(context.getString(R.string.base_url))
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient.build())
//                .build();
//        webApi = retrofit.create(webApi.class);



    }

    public void addContact(String name){

        webApi.postContact(new ContactPost(name,"0",""),"Bearer "+manager.fetchAuthToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Log.d("TAG", "onResponse: succed");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("TAG", "onResponse: failed");
            }
        });


    }

    public void getContacts(contacts_adapter adapter){
        final List<contact>[] ref = new List[]{null};
        contact c;
        Call<List<contact>> contacts = webApi.getContacts("Bearer "+manager.fetchAuthToken());
//        try{
//            return contacts.execute().body();
//        } catch (Throwable t){
//            return new ArrayList<contact>();
//        }

        contacts.enqueue(new Callback<List<contact>>() {
            @Override
            public void onResponse(Call<List<contact>> call, Response<List<contact>> response) {
                adapter.setContacts(response.body());
                adapter.notifyDataSetChanged();
                //response.body().get(0).getId();
            }

            @Override
            public void onFailure(Call<List<contact>> call, Throwable t) {

            }
        });
        return;
    }


    public void getMessage(message_adapter adapter,String id) {
        Call<List<message>> call = webApi.getMessages(id,"Bearer "+manager.fetchAuthToken());
        call.enqueue((new Callback<List<message>>() {
            @Override
            public void onResponse(Call<List<message>> call, Response<List<message>> response) {
                adapter.setMessages(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<message>> call, Throwable t) {

            }
        }));
    }
    public void postMessage(String mes,String id, message_adapter adapter){
        Call<Void> call = webApi.addMessages(id,new messagePost(mes),"Bearer "+manager.fetchAuthToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getMessage(adapter,id);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    public boolean login(String name, String password,String newToken, Context login){
        // call -> async



        Call<LoginResponse> call = webApi.login_String(name, password,newToken);
        final boolean[] isLogin = {false};
        call.enqueue(new Callback<LoginResponse>() {
            private static final String TAG = "";

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //when the request http succeed
                Log.d(TAG, "here we go sfsdfgd  "+ response.body().getToken());


                if (!response.body().getToken().equals("ERROR")){
                    manager.saveAuthToken(response.body().getToken());


                    Intent i = new Intent(login, Converstaions_List.class);
                    login.startActivity(i);
                }




                isLogin[0] = true;
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //when the request http failed
                Log.d(TAG, "onResponse: ");
                isLogin[0] = false;
            }
        });
        return isLogin[0];



    }

    public boolean register(String username,String nickname, String password,String newToken, Context login){
        // call -> async

        Call<LoginResponse> call = webApi.register(username,nickname, password,newToken);
        final boolean[] isLogin = {false};
        call.enqueue(new Callback<LoginResponse>() {
            private static final String TAG = "";

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //when the request http succeed
                Log.d(TAG, "here we go sfsdfgd  "+ response.body().getToken());


                if (!response.body().getToken().equals("ERROR")){
                    manager.saveAuthToken(response.body().getToken());


                    Intent i = new Intent(login, Converstaions_List.class);
                    login.startActivity(i);
                }




                isLogin[0] = true;
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //when the request http failed
                Log.d(TAG, "onResponse: ");
                isLogin[0] = false;
            }
        });
        return isLogin[0];



    }

}
