package nj.weatherAPI.commands;

import nj.weatherAPI.app_service.ConfigManager;
import nj.weatherAPI.client.WeatherForecastClient;
import nj.weatherAPI.weather_model.WeatherAlertResponse;
import nj.weatherAPI.weather_model.WeatherCurrentResponse;
import nj.weatherAPI.weather_model.WeatherForecastResponse;
import nj.weatherAPI.weather_service.WeatherService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ShellComponent
public class WeatherForecastCommand {

    private final WeatherForecastClient weatherClient;
    private final WeatherService weatherService;
    private final ConfigManager configmanager;
    private final ConfigManager configManager;

    public  WeatherForecastCommand(WeatherForecastClient weatherForecastClient,
                                   WeatherService weatherService,
                                   ConfigManager configManager) {
        this.configmanager = configManager;
        this.weatherClient = weatherForecastClient;
        this.weatherService = weatherService;
        this.configManager = configManager;
    }

    @ShellMethod(key="current", value="The current weather of a specific place")
        public String getCurrentWeather(
                @ShellOption(defaultValue = ShellOption.NULL) String location){
        try {
            if(location == null){
                location = configmanager.getDefaultLocation();
            }

            WeatherCurrentResponse response = weatherClient.current(configmanager.getApiKey(), location, "no");
            return "Location:" + location + weatherService.currentWeather(response);
        }catch (WebClientResponseException e){
            return "Error: " + e.getStatusCode() + ": " + e.getMessage();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(key="future", value="The weather forecast for the upcoming days")
    public String getFutureWeather(
            @ShellOption(defaultValue = ShellOption.NULL) String location,
            @ShellOption(defaultValue = ShellOption.NULL) String days
            ){

        try {
            if (location == null || location.isBlank()) {
                location = configmanager.getDefaultLocation();
            }

            if (days == null || days.isBlank()) {
                days = configmanager.getDefaultDays();
            }

            WeatherForecastResponse response = weatherClient.forecast(configmanager.getApiKey(), location, Integer.valueOf(days), "no", "no");
            return weatherService.forecastWeather(response);
        }catch (WebClientResponseException e){
            return "Error: " + e.getStatusCode() + ": " + e.getMessage();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod(key="alerts", value="The weather alerts ")
    public String getAlerts(
            @ShellOption(defaultValue = ShellOption.NULL) String location
    ){
        try{
            if (location == null || location.isBlank()) {
                location = configmanager.getDefaultLocation();
            }

            WeatherAlertResponse alert = weatherClient.alert(configManager.getApiKey(), location);
            return weatherService.alertWeather(alert);
        }catch (WebClientResponseException e){
            return "Error: " + e.getStatusCode() + ": " + e.getMessage();
        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
}
