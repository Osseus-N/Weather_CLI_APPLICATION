package nj.weatherAPI.config;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


@Configuration
public class DefaultSettingConfig {

    private final Path configPath = Path.of(
            System.getProperty("user.home"), ".weather-cli", "config.properties"
    );

    public WeatherConfig loadProperties()throws IOException{

        Properties props = new Properties();
            if (Files.exists(configPath)) {
                try (InputStream in = Files.newInputStream(configPath)) {
                    props.load(in);
                }
            }
            return props;
        }
    }
}
