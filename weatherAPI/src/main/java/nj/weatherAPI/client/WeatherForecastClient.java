package nj.weatherAPI.client;


import nj.weatherAPI.model.WeatherCurrentResponse;
import nj.weatherAPI.model.WeatherForecastResponse;
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
}