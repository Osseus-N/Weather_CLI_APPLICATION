package nj.weatherAPI.commands;

import nj.weatherAPI.properties_model.WeatherProperties;
import nj.weatherAPI.service.ConfigService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ConfigCommands {

    private final ConfigService configService;
    private final WeatherProperties weatherProperties;

    public ConfigCommands(ConfigService configService, WeatherProperties weatherProperties) {
        this.configService = configService;
        this.weatherProperties = weatherProperties;
    }

    @ShellMethod(key="see", value = "See the Default Settings")
    public String seeConfig() {
        return configService.seeConfig(weatherProperties);
    }

    @ShellMethod(key="edit" ,value = "Edit the Default Setting")
    public String editConfig() {
        return "Edit Config";
    }

}
