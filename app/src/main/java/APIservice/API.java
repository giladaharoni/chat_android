package APIservice;

import com.google.gson.annotations.SerializedName;

public class API {
    @SerializedName("joke")
    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke){
        this.joke = joke;
    }

}
