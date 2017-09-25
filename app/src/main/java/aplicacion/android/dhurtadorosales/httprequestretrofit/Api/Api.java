package aplicacion.android.dhurtadorosales.httprequestretrofit.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import aplicacion.android.dhurtadorosales.httprequestretrofit.Api.Deserializers.MyDeserializer;
import aplicacion.android.dhurtadorosales.httprequestretrofit.Models.City;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String BASE_ICONS = "http://openweathermap.org/img/w/";
    public static final String EXTENSION_ICONS = ".png";

    private static Retrofit retrofit = null;
    public static final String APPKEY = "1df4fe12bb920c79c05709de1d7e3276";

    public static Retrofit getApi() {
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(City.class, new MyDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }

        return retrofit;
    }

}
