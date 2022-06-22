package APIservice;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import viewmodels.contact;
import viewmodels.message;

public interface webApi {
    @GET("/Contacts")
    Call<List<contact>> getContacts(@Header("Authorization") String authHeader);

    @POST("/Contacts")
    Call<Void> postContact(@Body ContactPost p,@Header("Authorization") String authHeader);


    @GET("/Contacts/{id}/messages")
    Call<List<message>> getMessages(@Path("id") String id,@Header("Authorization") String authHeader);

    @POST("/Contacts/{id}/messages")
    Call<Void> addMessages(@Path("id") String id,  @Body messagePost m,@Header("Authorization") String authHeader);

    @POST("/api/User")
    Call<Void> login(@Query("username") String username, @Query("password") String password);

    @GET("/string")
    Call<LoginResponse> login_String(@Query("username") String username, @Query("password") String password);

    @GET("/registerwithlogin")
    Call<LoginResponse> register(@Query("username") String username,@Query("nickname") String nickname,@Query("password") String password);

    @GET("/WeatherForecast")
    Call <String> tryout();





}
