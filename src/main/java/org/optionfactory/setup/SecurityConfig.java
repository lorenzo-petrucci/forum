package org.optionfactory.setup;

import org.optionfactory.author.AuthorDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(daoAuthenticationProvider)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/api/v1/message/private")).authenticated()
                        .requestMatchers(mvc.pattern("/api/v1/message/public")).permitAll()
                        .requestMatchers(mvc.pattern("/api/v1/author/**")).permitAll()
                );
        return http.build();
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public HandlerMappingIntrospector introspector() {
        return new HandlerMappingIntrospector();
    }

    @Bean
    public AuthorDetailsService authorDetailsService() {
        return new AuthorDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(AuthorDetailsService authorDetailsService) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(authorDetailsService);
        return daoAuthenticationProvider;
    }
}

