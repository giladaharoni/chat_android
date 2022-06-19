package APIservice;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chat_android.R;

public class SessionManager {
    private SharedPreferences prefs = MyApplication.context.getSharedPreferences(MyApplication.context.getString(R.string.app_name), Context.MODE_PRIVATE);


    public static String USER_TOKEN = "user_token";

    void saveAuthToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }


    String fetchAuthToken(){
        return prefs.getString(USER_TOKEN, null);
    }
}
