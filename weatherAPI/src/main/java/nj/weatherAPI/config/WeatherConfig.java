package nj.weatherAPI.config;

import nj.weatherAPI.client.WeatherForecastClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
public class WeatherConfig {

    private final String url;

    public WeatherConfig(@Value("${weather.api.url}")String url){
        this.url = url;
    }

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory() {
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Accept", "application/json")
                .build();

        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }

    @Bean
    WeatherForecastClient weatherForecastClient(HttpServiceProxyFactory factory) {
        return factory.createClient(WeatherForecastClient.class);
    }
}
