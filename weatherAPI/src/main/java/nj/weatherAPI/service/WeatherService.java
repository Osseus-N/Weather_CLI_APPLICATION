package nj.weatherAPI.service;

import nj.weatherAPI.weather_model.WeatherAlertResponse;
import nj.weatherAPI.weather_model.WeatherCurrentResponse;
import nj.weatherAPI.weather_model.WeatherForecastResponse;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    public String currentWeather(WeatherCurrentResponse response){
         String name = response.current().condition().text();
         Double temp = response.current().tempC();
         Double humidity = response.current().humidity();
         LocalDateTime date = response.current().lastUpdated();

         return String.format(
                 "%n📅 Date: %s%n" +
                         "🌤 Condition: %s%n" +
                         "🌡 Temperature: %.2f°C%n" +
                         "💧 Humidity: %.2f%%%n",
                 date,
                 name,
                 temp,
                 humidity
         );
    }

    public String forecastWeather(WeatherForecastResponse response){
        List<WeatherForecastResponse.ForecastDay> forecasts = response.forecast().forecastDays();

        StringBuilder result = new StringBuilder();

        forecasts.forEach(forecast -> {
            result.append(
                    String.format(
                    "%n📅 %s | 🌡 Max: %.1f°C | Min: %.1f°C | 🌧 %d%%",
                    forecast.date(),
                    forecast.day().maxTemp(),
                    forecast.day().minTemp(),
                    forecast.day().chance()
            ));
        });
        return result.toString();
    }

    public String alertWeather(WeatherAlertResponse response){

        if(response.alert() == null){
            return "No alerts at this time";
        }

        String alert = response.alert().headline();
        String effective =  response.alert().effective();
        String areas =  response.alert().areas();
        String certainty =  response.alert().certainty();
        String instructions = response.alert().instructions();

        return String.format(
                "%n⚠️ ALERT: %s%n" +
                "📅 Effective: %s%n" +
                "🚨 Severity: %s%n" +
                "🎯 Certainty: %s%n" +
                "📍 Areas: %s%n" +
                "📝 Instruction: %s%n",
                        alert,
                        effective,
                        areas,
                        certainty,
                        instructions
        );
    }

}