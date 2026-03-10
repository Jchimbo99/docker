package com.itsqmet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfiguracion {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracion = new CorsConfiguration();

        configuracion.setAllowedOrigins(List.of("http://localhost:4200"));

        configuracion.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));

        configuracion.setAllowedHeaders(List.of("*"));

        configuracion.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuracion);

        return source;
    }

}
