package aplicacion.android.dhurtadorosales.httprequestretrofit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import aplicacion.android.dhurtadorosales.httprequestretrofit.Api.Api;
import aplicacion.android.dhurtadorosales.httprequestretrofit.Api.ApiServices.WeatherService;
import aplicacion.android.dhurtadorosales.httprequestretrofit.Models.City;
import aplicacion.android.dhurtadorosales.httprequestretrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText EditTextSearch;
    private TextView textViewCity;
    private TextView textViewDescription;
    private TextView textViewTemp;
    private ImageView img;
    private Button btn;

    private WeatherService service;
    private Call<City> cityCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();

        //Instancia del servicio
        service = Api.getApi().create(WeatherService.class);

        //Evento botón
        btn.setOnClickListener(this);
    }

    private void setUI() {
        EditTextSearch = (EditText) findViewById(R.id.editTextSearch);
        textViewCity = (TextView) findViewById(R.id.textViewCity);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewTemp = (TextView) findViewById(R.id.textViewTemperature);
        img = (ImageView) findViewById(R.id.imageViewIcon);
        btn = (Button) findViewById(R.id.buttonSearch);
    }

    private void setResult(City city) {
        textViewCity.setText(city.getName() + ", " + city.getCountry());
        textViewDescription.setText(city.getTemperature() + "ºC");
        Picasso.with(this).load(Api.BASE_ICONS + city.getIcon() + Api.EXTENSION_ICONS).into(img);
    }

    @Override
    public void onClick(View view) {
        String city = EditTextSearch.getText().toString();
        if (city != "") {
            cityCall = service.getCity(city, Api.APPKEY, "metric", "es");
            cityCall.enqueue(new Callback<City>() {
                @Override
                public void onResponse(Call<City> call, Response<City> response) {
                    City city = response.body();
                    setResult(city);
                }

                @Override
                public void onFailure(Call<City> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
