package nj.weatherAPI.weather_model;

public record WeatherAlertResponse(Weather.Location location,Alert alert){

    public record Alert(
            String headline,
            String severity,
            String urgency,
            String effective,
            String areas,
            String certainty,
            String instructions
    ){ }
}
