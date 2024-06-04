package project.medconnect;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500/", "http://deti-tqs-14.ua.pt:5500/", "http://127.0.0.1:8080", "http://deti-tqs-14.ua.pt:8080", "http://deti-tqs-14.ua.pt:3001", "http://deti-tqs-14.ua.pt:3002", "http://deti-tqs-14.ua.pt:3003")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}