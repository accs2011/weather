package ir.alirezaebrahimi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ir.alirezaebrahimi.wheather.Weather;


import static ir.alirezaebrahimi.R.drawable.abri;
import static ir.alirezaebrahimi.R.drawable.abriaftabi;
import static ir.alirezaebrahimi.R.drawable.aftabi;
import static ir.alirezaebrahimi.R.drawable.background;
import static ir.alirezaebrahimi.R.drawable.baran;
import static ir.alirezaebrahimi.R.drawable.barf;
import static ir.alirezaebrahimi.R.drawable.ice;
import static ir.alirezaebrahimi.R.drawable.moon;
import static ir.alirezaebrahimi.R.drawable.wind;

public class MainActivity extends AppCompatActivity {
public void connecttosite(String city)
{
    AsyncHttpClient client = new AsyncHttpClient();
    String url = "http://phoenix-iran.ir/Files_php_App/WeatherApi/newApiWeather.php?city="+city+"";
    System.out.println(url);
    client.get(url, new JsonHttpResponseHandler() {


                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    final TextView cityview=findViewById(R.id.cityshow);
                    final TextView temp=findViewById(R.id.temp);
                    final TextView text=findViewById(R.id.text);
                    Gson gson = new Gson();
                    Weather weather=gson.fromJson(response.toString(), Weather.class);
                    System.out.println("Received event with data: " + response);
                     int temp1=weather.getResult().getCondition().getTemperature();
                     temp1= (int) ((temp1-32)/1.8);

                  String stemp= String.valueOf(temp1)+" °C";
                    temp.setText(stemp);
                    String stringcityview=weather.getResult().getLocation().getCountry()+" "+weather.getResult().getLocation().getCity();
                     cityview.setText(stringcityview);
                    String sky=weather.getResult().getCondition().getText();
                    text.setText(sky);
                    LinearLayout ln=findViewById(R.id.background);
                    switch (sky){
                        case "Sunny":

                            ln.setBackgroundResource(aftabi);
                            break;
                        case "Mostly Sunny":
                            ln.setBackgroundResource(aftabi);
                            break;
                        case "Snow":
                            ln.setBackgroundResource(barf);
                            break;
                        case "Snow Flurries":
                            ln.setBackgroundResource(barf);
                            break;
                        case "Light Snow Showers":
                            ln.setBackgroundResource(barf);
                            break;
                        case "Blowing Snow":
                            ln.setBackgroundResource(barf);
                            break;
                        case "Mixed Rain And Snow":
                            ln.setBackgroundResource(baran);
                            break;
                        case "Mixed Rain And Sleet":
                            ln.setBackgroundResource(abriaftabi);
                            break;
                        case "Freezing Rain":
                            ln.setBackgroundResource(baran);
                            break;
                        case "Rain":
                            ln.setBackgroundResource(baran);
                        case "Cold":
                            ln.setBackgroundResource(abri);
                            break;
                        case "Freezing Drizzle":
                            ln.setBackgroundResource(ice);
                            break;
                        case "Showers":
                            ln.setBackgroundResource(baran);
                            break;
                        case "Hail":
                            ln.setBackgroundResource(baran);
                            break;
                        case "Windy":
                            ln.setBackgroundResource(wind);
                            break;
                        case "Hot":
                            ln.setBackgroundResource(aftabi);
                            break;
                        case "Heavy Snow":
                            ln.setBackgroundResource(barf);
                            break;
                        case "Partly Cloudy":
                            ln.setBackgroundResource(abriaftabi);
                            break;
                        case "Cloudy":
                            ln.setBackgroundResource(abri);
                            break;
                        case "Mostly Cloudy":
                            ln.setBackgroundResource(abri);
                            break;
                        case "Scattered Showers":
                            ln.setBackgroundResource(baran);
                            break;
                        case "Clear":
                            ln.setBackgroundResource(moon);
                            break;
                        default:
                            ln.setBackgroundResource(background);
                            break;
                    }



                }
                  });}


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText  city=findViewById(R.id.city);


        final Button btnsearch=findViewById(R.id.btnsearch);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String cityview=city.getText().toString();
                System.out.println(cityview);
                if(cityview.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter corect city name",Toast.LENGTH_LONG);
                else
                {
                    connecttosite(cityview);

        }}});

                                     }}
