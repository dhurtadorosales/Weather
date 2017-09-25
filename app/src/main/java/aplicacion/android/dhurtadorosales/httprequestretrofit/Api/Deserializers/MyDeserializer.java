package aplicacion.android.dhurtadorosales.httprequestretrofit.Api.Deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import aplicacion.android.dhurtadorosales.httprequestretrofit.Models.City;

public class MyDeserializer implements JsonDeserializer<City> {
    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject obj = json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject();

        int id = json.getAsJsonObject().get("id").getAsInt();
        String name = json.getAsJsonObject().get("name").getAsString();
        String country = json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString();
        String icon = obj.get("icon").getAsString();
        String description = obj.get("description").getAsString();
        int temp = json.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsInt();

        City city = new City(id, name, country, icon, description, temp);
        return city;
    }
}
