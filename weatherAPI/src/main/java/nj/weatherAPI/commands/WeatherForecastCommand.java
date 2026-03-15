package nj.weatherAPI.commands;

import nj.weatherAPI.client.WeatherForecastClient;
import nj.weatherAPI.model.WeatherCurrentResponse;
import nj.weatherAPI.model.WeatherForecastResponse;
import nj.weatherAPI.service.WeatherCurrentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class WeatherForecastCommand {

    private String apiKey;
    private final WeatherForecastClient weatherClient;
    private final WeatherCurrentService weatherService;

    public  WeatherForecastCommand(WeatherForecastClient weatherForecastClient,
                                    WeatherCurrentService weatherService,
                                   @Value("${weather.api.key}") String apiKey) {
        this.weatherClient = weatherForecastClient;
        this.weatherService = weatherService;
        this.apiKey = apiKey;
    }

    @ShellMethod(key="current", value="The current weather of a specific place")
        public String getCurrentWeather(
                @ShellOption(defaultValue = "San Jose") String location,
                @ShellOption(defaultValue = "no") String aqi) {
        WeatherCurrentResponse response = weatherClient.current(apiKey, location, aqi);

            return "Location:"+ location +weatherService.currentWeather(response);
    }

    @ShellMethod(key="future", value="The weather forecast for the upcoming days")
    public void getFutureWeather(
            @ShellOption(defaultValue = "San Jose") String location,
            @ShellOption(defaultValue = "2") Integer days,
            @ShellOption(defaultValue = "no") String aqi,
            @ShellOption(defaultValue = "yes") String alert
            ){
        WeatherForecastResponse response = weatherClient.forecast(apiKey, location ,days, aqi,alert);
        weatherService.forecastWeather(response);
    }
}
