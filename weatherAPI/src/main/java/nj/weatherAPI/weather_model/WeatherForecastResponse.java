package nj.weatherAPI.weather_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherForecastResponse (Weather.Location location,
                                       @JsonProperty("forecast")
                                       Forecast forecast){

    public record Forecast(
        @JsonProperty("forecastday") List<ForecastDay> forecastDays
    ){}

    public record ForecastDay(String date,
                       Day day
                       ){}


    public record Day(@JsonProperty("maxtemp_c") Double maxTemp,
               @JsonProperty("mintemp_c") Double minTemp,
               @JsonProperty("daily_chance_of_rain") Integer chance
    ){}

}

