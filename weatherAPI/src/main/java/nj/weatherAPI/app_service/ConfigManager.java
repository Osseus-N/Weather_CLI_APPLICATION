package nj.weatherAPI.app_service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

@Service
public class ConfigManager {


    private final Path configPath;
    private final AppConfig appConfig;
    private final Scanner  scanner= new Scanner(System.in);

    public ConfigManager(AppConfig appConfig, ConfigPathProvider ConfigPathProvider) {
        this.appConfig = appConfig;
        this.configPath = ConfigPathProvider.getConfigPath();
    }

    public String setDefault(String setting) throws IOException {

        Properties props = appConfig.load();

        switch (setting) {
            case "all":
                askApiKey();
                askDefaultLocation();
                askDefaultDays();
                break;
            case "days":
                askDefaultDays();
                break;
            case "location":
                askDefaultLocation();
                break;
            case "api":
                askApiKey();
                break;
        }
        return "successfully sets property: " + setting;

    }

    private void askApiKey() throws IOException {
        System.out.print("Enter your OpenWeather API key: ");
        String key = scanner.nextLine().trim();

        while (key.isEmpty()) {
            System.out.print("API key cannot be empty. Try again: ");
            key = scanner.nextLine().trim();
        }

        appConfig.set("weather.api.key", key);
    }

    private void askDefaultLocation() throws IOException {
        System.out.print("Default location [Manila]: ");
        String location = scanner.nextLine().trim();

        appConfig.set("weather.default-location",
                location.isEmpty() ? "Manila" : location
        );
    }

    private void askDefaultDays() throws IOException {
        System.out.print("Default forecast days (1-14) [3]: ");
        String input = scanner.nextLine().trim();

        String days = "3";

        if (!input.isEmpty()) {
            try {
                int parsed = Integer.parseInt(input);
                if (parsed >= 1 && parsed <= 7) {
                    days = String.valueOf(parsed);
                } else {
                    System.out.println("Invalid range, using default: 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a number, using default: 3");
            }
        }

        appConfig.set("weather.default-days", days);
    }
    public String getDefaultDays() {
        try {
            String val = appConfig.get("weather.default-days", "3");
            return val.matches(".*[^0-9].*") ? "3" : val;
        } catch (IOException e) {
            return "3";
        }
    }
    public String getDefaultLocation() {

        try {
            return appConfig.get("weather.default-location", "Manila");
        } catch (IOException e) {
            return "Manila";
        }
    }
    public String getApiKey() {
        try {
            return appConfig.get("weather.api.key", "MISSING_KEY");
        } catch (IOException e) {
            return "MISSING_KEY";
        }
    }

    public String getAll() throws IOException {
        StringBuilder sb = new StringBuilder();

        String days = appConfig.get("weather.default-days", "3");
        sb.append("\uD83D\uDCC5 Days: ").append(days).append(System.lineSeparator());

        String fullKey = appConfig.get("weather.api.key", "None");
        sb.append("\uD83D\uDD11 API Key: ");

        if (fullKey.length() > 6) {
            sb.append(fullKey, 0, 6).append("******");
        } else {
            sb.append(fullKey);
        }
        sb.append(System.lineSeparator());

        String city = appConfig.get("weather.default-city", "Manila");
        sb.append("\uD83D\uDCCD City: ").append(city).append(System.lineSeparator());

        return sb.toString();
    }
    public boolean isFileCreated(){
        return Files.exists(configPath);
    }
    public void createConfigPath(){

        try {
            if(configPath.getParent() != null) {
                Files.createDirectories(configPath.getParent());
                System.out.println("Directory created.");
            }

            if (Files.notExists(configPath)) {
                Files.createFile(configPath);
                System.out.println("Config created: " + configPath);
            }

        } catch (IOException e) {
            System.err.println("Could not initialize config: " + e.getMessage());
        }
    }

}

