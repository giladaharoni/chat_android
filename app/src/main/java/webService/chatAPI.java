package webService;

import android.telecom.Call;

import java.util.List;

import viewmodels.contact;

public interface chatAPI {
    @GET("contacts");
    Call<List<contact>> getContacts();
    @POST("contacts");
    Call<Void> postContacts(@Body contact c);
}
