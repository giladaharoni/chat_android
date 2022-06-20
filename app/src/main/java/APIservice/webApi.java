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
    @GET("/api/contacts")
    Call<List<contact>> getContacts();

    @POST("/api/contacts")
    Call<Void> postContact(@Body contact c);

    @GET("/api/contacts/{id}/messages")
    Call<List<message>> getMessages(@Path("id") String id);

    @POST("/api/contacts/{id}/messages")
    Call<Void> addMessages(@Path("id") String id, @Body message m);

    @POST("/api/User")
    Call<Void> login(@Query("username") String username, @Query("password") String password);

    @POST("/register")
    Call<Void> login(@Query("username") String username,@Query("nickname") String nickname,@Query("password") String password);







}
