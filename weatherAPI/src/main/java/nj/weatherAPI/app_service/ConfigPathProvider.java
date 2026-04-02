package nj.weatherAPI.app_service;

import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class ConfigPathProvider {

    private final Path  configPath;
    public ConfigPathProvider(){
        this.configPath = Path.of(System.getProperty("user.home")
                ,".weather-cli"
                ,"config.properties");

    }
    public Path getConfigPath(){
        return configPath;
    }
}
