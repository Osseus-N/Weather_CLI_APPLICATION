package nj.weatherAPI.app_service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;


@Service
public class AppConfig {


    private final Path configPath;

    public AppConfig(ConfigPathProvider configPathProvider) {
        this.configPath = configPathProvider.getConfigPath();
    }
    public String get(String key, String fallback) throws IOException {
        return load().getProperty(key, fallback);
    }
    public void set(String key, String value) throws IOException {
        Properties props = load();
        props.setProperty(key, value);
        try (OutputStream out = Files.newOutputStream(configPath)) {
            props.store(out, "weather-cli config");
        }
    }
    public Properties load() throws IOException {
        Properties props = new Properties();
        if (Files.exists(configPath)) {
            try (InputStream in = Files.newInputStream(configPath)) {
                props.load(in);
            }
        }
        return props;
    }

}