package nj.weatherAPI.commands;

import nj.weatherAPI.app_service.ConfigManager;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class ConfigCommands {

    private final ConfigManager configmanager;

    public ConfigCommands(ConfigManager configmanager ) {
        this.configmanager = configmanager;
    }

    @ShellMethod(key="see", value = "See the Default Settings")
    public String seeAllConfig() throws IOException {

        if(configmanager.isFileCreated()){
            System.out.println("Current Properties:");
            return configmanager.getAll();
        }
        return "No config found: " + "Use edit command to have a default configurations";
    }

    @ShellMethod(key="edit" ,value = "Edit the Default Setting")
    public String editConfig(
            @ShellOption(defaultValue = "all") String settings
    ) throws IOException {
        if(configmanager.isFileCreated()){
            return configmanager.setDefault(settings);
        }
        configmanager.createConfigPath();
        return "Creating Directory and File";
    }

}
