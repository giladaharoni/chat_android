package APIservice;

import static APIservice.MyApplication.context;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chat_android.Converstaions_List;
import com.example.chat_android.R;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;

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
import viewmodels.contact;


public class WebService {
    Retrofit retrofit;
    webApi webApi;
    SessionManager manager;

    public WebService() {
        manager = new SessionManager();



        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        CookieManager cookieHandler = new CookieManager();
        cookieHandler.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieHandler));
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                okhttp3.Response response = chain.proceed(original);
                String authToken = "Bearer " + manager.fetchAuthToken();


                Request request = original.newBuilder()
                        .header("Authorization", authToken)
                        .method(original.method(), original.body()).build();

                return chain.proceed(request);
            }
        }).addInterceptor(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                //.addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
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



    public boolean login(String name, String password, Context login){
        // call -> async


        Call<Void> call = webApi.login(name, password);
        final boolean[] isLogin = {false};
        call.enqueue(new Callback<Void>() {
            private static final String TAG = "";

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //when the request http succeed
                Log.d(TAG, "here we go sfsdfgd  "+ response.raw());


                if (response.isSuccessful()){

                    Headers token = response.headers();




                    Intent i = new Intent(login, Converstaions_List.class);
                    login.startActivity(i);
                }





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
