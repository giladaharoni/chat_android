package APIservice;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import viewmodels.contact;
import viewmodels.message;

public interface webApi {
    @GET("/api/Contacts")
    Call<List<contact>> getContacts();

    @POST("/api/Contacts")
    Call<Void> postContact(@Body contact c);

    @GET("/api/Contacts/{id}/messages")
    Call<List<message>> getMessages(@Path("id") String id);

    @POST("/api/Contacts/{id}/messages")
    Call<Void> addMessages(@Path("id") String id, @Body message m);

    @POST("/api/User")
    Call<Void> login(@Query("username") String username, @Query("password") String password);

    @POST("/register")
    Call<Void> register(@Query("username") String username,@Query("nickname") String nickname,@Query("password") String password);







}
