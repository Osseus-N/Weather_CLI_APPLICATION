package nj.weatherAPI.properties_model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Validated
@PropertySource("classpath:configuration.properties")
@ConfigurationProperties(prefix = "weather.default")
public record WeatherProperties(
            @NotBlank(message = "cannot be empty")
            String location,
            String aqi,
            @Size( min=1, max=14, message = "Days must be between 1 to 14")
            String days
) {}
