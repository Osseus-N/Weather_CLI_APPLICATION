# Weather_CLI_APPLICATION
A CLI application using Spring Boot that can be used for getting forecast weather and alerts.

## Available Commands

╔═══════════════════════════════════════════════════════════════╗
║                    WEATHER CLI - COMMANDS                     ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║  SETTINGS                                                     ║
║  ──────────────────────────────────────────────────────────  ║
║  settings                 See the default settings.           ║
║                                                               ║
║  edit-settings            Edit the default settings.          ║
║    --api,      -a         The API key for weather data.       ║
║    --location, -l         Default location if none given.     ║
║    --day,      -d         Default number of forecast days.    ║
║                                                               ║
║  FORECAST                                                     ║
║  ──────────────────────────────────────────────────────────  ║
║  current                  Show the current weather forecast.  ║
║    --location, -l         Location (uses default if omitted). ║
║                                                               ║
║  future                   Forecast for upcoming days.         ║
║    --location, -l         Target location for the forecast.   ║
║    --day,      -d         Number of upcoming days to fetch.   ║
║                                                               ║
║  report                   See reports if any exist.           ║
║    --location, -l         Location to check for reports.      ║
║                                                               ║
╚═══════════════════════════════════════════════════════════════╝

## How to Use

### Prerequisites
- Java 21 or higher installed
- A valid weather API key

### Running the app
```bash
java -jar target/myproject-0.0.1-SNAPSHOT.jar
```

### First run
On first launch, the app will automatically create a config file at:
