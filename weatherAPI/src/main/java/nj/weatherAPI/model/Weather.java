package nj.weatherAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record Weather (Location location, WeatherData current) {

    public record Location(
            String name,
            String region,
            String country
    ){}
    public record WeatherData(
                    Double humidity,
                    @JsonProperty("temp_c") Double tempC,

                    @JsonProperty("last_updated")
                    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
                              LocalDateTime lastUpdated,

                    Condition condition)
    {}

    public record Condition(String text){}

}
