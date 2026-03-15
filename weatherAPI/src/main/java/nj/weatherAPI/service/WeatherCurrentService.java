package nj.weatherAPI.service;

import nj.weatherAPI.client.WeatherForecastClient;
import nj.weatherAPI.config.WeatherConfig;
import nj.weatherAPI.model.WeatherCurrentResponse;
import nj.weatherAPI.model.WeatherForecastResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherCurrentService{

    public String currentWeather(WeatherCurrentResponse response){
         String name = response.current().condition().text();
         Double temp = response.current().tempC();
         Double humidity = response.current().humidity();
         LocalDateTime date = response.current().lastUpdated();

         return String.format("%nCondition:%s %nTemperature:%.2f %nHumidity:%.2f %nDate:%s%n ", name, temp, humidity, date);

    }

    public void forecastWeather(WeatherForecastResponse response){
        List<WeatherForecastResponse.ForecastDay> forecasts = response.forecast().forecastDays();

        forecasts.forEach(forecast -> {
            System.out.printf(
                    "%n📅 %s | 🌡 Max: %.1f°C | Min: %.1f°C | 🌧 %d%%%n",
                    forecast.date(),
                    forecast.day().maxTemp(),
                    forecast.day().minTemp(),
                    forecast.day().chance()
            );
        });
    }


}