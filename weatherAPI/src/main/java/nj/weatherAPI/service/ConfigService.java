package nj.weatherAPI.service;

import nj.weatherAPI.properties_model.WeatherProperties;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    public String seeConfig(WeatherProperties properties){

        return String.format("""
            ✅ Setup complete!
            📍 Location : %s
            📅 Days     : %d
            🔑 AQI      : %s
            """, properties.location(), properties.days(), properties.aqi());
    }

    public void editConfig(){}
}
