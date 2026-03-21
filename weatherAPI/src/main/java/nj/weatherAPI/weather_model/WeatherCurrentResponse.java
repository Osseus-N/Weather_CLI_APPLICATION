package nj.weatherAPI.weather_model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherCurrentResponse(Weather.Location location, @JsonProperty("current") Weather.WeatherData current) {
}
