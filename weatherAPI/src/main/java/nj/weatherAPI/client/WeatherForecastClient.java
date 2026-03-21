package nj.weatherAPI.client;


import nj.weatherAPI.weather_model.WeatherAlertResponse;
import nj.weatherAPI.weather_model.WeatherCurrentResponse;
import nj.weatherAPI.weather_model.WeatherForecastResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface WeatherForecastClient {

    @GetExchange("/current.json")
    WeatherCurrentResponse current(
            @RequestParam("key") String key,
            @RequestParam("q") String location,
            @RequestParam("aqi") String aqi
    );

    @GetExchange("/forecast.json")
    WeatherForecastResponse forecast(
            @RequestParam("key") String key,
            @RequestParam("q") String location,
            @RequestParam("days") Integer days,
            @RequestParam("aqi") String aqi,
            @RequestParam("alert") String alert
    );

    @GetExchange("/alerts.json")
    WeatherAlertResponse alert(
            @RequestParam("key") String key,
            @RequestParam("q") String location
    );
}