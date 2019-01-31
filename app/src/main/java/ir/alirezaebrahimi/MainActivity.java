package ir.alirezaebrahimi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import ir.alirezaebrahimi.wheather.Weather;

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

                    ImageView imageView=findViewById(R.id.imageView);
                        switch (sky){
                            case "Sunny":
                               imageView.setImageResource(R.drawable.aftabi);
                                break;
                            case "Mostly Sunny":
                                imageView.setImageResource(R.drawable.aftabi);
                                break;
                            case "Snow":
                                imageView.setImageResource(R.drawable.barfi);
                                break;
                            case "Snow Flurries":
                                imageView.setImageResource(R.drawable.barfi);
                                break;
                            case "Light Snow Showers":
                                imageView.setImageResource(R.drawable.barfi);
                                break;
                            case "Blowing Snow":
                                imageView.setImageResource(R.drawable.barfi);
                                break;
                            case "Mixed Rain And Snow":
                                imageView.setImageResource(R.drawable.barfibarani);
                                break;
                            case "Mixed Rain And Sleet":
                                imageView.setImageResource(R.drawable.abriaftabi);
                                break;
                            case "Freezing Rain":
                                imageView.setImageResource(R.drawable.barfibarani);
                                break;
                            case "Rain":
                                imageView.setImageResource(R.drawable.abribarani);
                            case "Cold":
                                imageView.setImageResource(R.drawable.abri);
                                break;
                            case "Freezing Drizzle":
                                imageView.setImageResource(R.drawable.yakh);
                                break;
                            case "Showers":
                                imageView.setImageResource(R.drawable.abribarani);
                                break;
                            case "Hail":
                                imageView.setImageResource(R.drawable.abribarani);
                                break;
                            case "Windy":
                                imageView.setImageResource(R.drawable.wind);
                                break;
                            case "Hot":
                                imageView.setImageResource(R.drawable.aftabi);
                                break;
                            case "Heavy Snow":
                                imageView.setImageResource(R.drawable.barfi);
                                break;
                            case "Partly Cloudy":
                                imageView.setImageResource(R.drawable.abriaftabi);
                                break;
                            case "Cloudy":
                                imageView.setImageResource(R.drawable.abri);
                                break;
                            case "Mostly Cloudy":
                                imageView.setImageResource(R.drawable.abri);
                                break;
                            case "Scattered Showers":
                                imageView.setImageResource(R.drawable.abribarani);
                                break;
                            case "Clear":
                                imageView.setImageResource(R.drawable.aftabi);
                                break;
                            default:
                                imageView.setImageResource(R.drawable.back1);
                                break;
                        }

                    System.out.println(stringcityview);

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
