package nj.weatherAPI;

import nj.weatherAPI.client.WeatherForecastClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class WeatherinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherinfoApplication.class, args);
	}

}
