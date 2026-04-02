package nj.weatherAPI.first_run;

import nj.weatherAPI.app_service.ConfigManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class FirstRunSetUp implements ApplicationRunner {

    private final ConfigManager config;


    public FirstRunSetUp(ConfigManager config) {
        this.config = config;
    }

    @Override
    public void run(ApplicationArguments args)throws Exception{

        if(config.isFileCreated()){
            return;
        }

        printWelcome();
        config.createConfigPath();
        config.setDefault("all");
        printDone();
    }

    private void printWelcome() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║   Welcome to Weather CLI! 🌤     ║");
        System.out.println("║   Let's get you set up.          ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println();
    }

    private void printDone() {
        System.out.println();
        System.out.println("✔ Setup complete! Config saved to ~/.weather-cli/config.properties");
        System.out.println("  You can change settings anytime with: weather config set <key> <value>");
        System.out.println();
    }
}

