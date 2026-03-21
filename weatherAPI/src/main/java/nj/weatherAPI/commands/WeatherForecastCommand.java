package nj.weatherAPI.commands;

import nj.weatherAPI.client.WeatherForecastClient;
import nj.weatherAPI.weather_model.WeatherAlertResponse;
import nj.weatherAPI.weather_model.WeatherCurrentResponse;
import nj.weatherAPI.weather_model.WeatherForecastResponse;
import nj.weatherAPI.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ShellComponent
public class WeatherForecastCommand {

    private String apiKey;
    private final WeatherForecastClient weatherClient;
    private final WeatherService weatherService;


    public  WeatherForecastCommand(WeatherForecastClient weatherForecastClient,
                                   WeatherService weatherService,
                                   @Value("${weather.api.key}") String apiKey
                                  ) {
        this.weatherClient = weatherForecastClient;
        this.weatherService = weatherService;
        this.apiKey = apiKey;
    }

    @ShellMethod(key="current", value="The current weather of a specific place")
        public String getCurrentWeather(
                @ShellOption(defaultValue = "San Jose") String location,
                @ShellOption(defaultValue = "no") String aqi) {
        try {
            WeatherCurrentResponse response = weatherClient.current(apiKey, location, aqi);
            return "Location:" + location + weatherService.currentWeather(response);
        }catch (WebClientResponseException e){
            return "Error: " + e.getStatusCode() + ": " + e.getMessage();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(key="future", value="The weather forecast for the upcoming days")
    public String getFutureWeather(
            @ShellOption(defaultValue = "San Jose") String location,
            @ShellOption(defaultValue = "2") int days,
            @ShellOption(defaultValue = "no") String aqi,
            @ShellOption(defaultValue = "no") String alert
            ){

        try {
            WeatherForecastResponse response = weatherClient.forecast(apiKey, location, days, aqi, alert);
            return weatherService.forecastWeather(response);
        }catch (WebClientResponseException e){
            return "Error: " + e.getStatusCode() + ": " + e.getMessage();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(key="alerts", value="The weather alerts ")
    public String getAlerts(
            @ShellOption(defaultValue = "San Jose") String location
    ){
        try{
            WeatherAlertResponse alert = weatherClient.alert(apiKey, location);
            return weatherService.alertWeather(alert);
        }catch (WebClientResponseException e){
            return "Error: " + e.getStatusCode() + ": " + e.getMessage();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
