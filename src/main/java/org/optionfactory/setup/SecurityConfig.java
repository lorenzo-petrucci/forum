package org.optionfactory.setup;

import org.optionfactory.author.AuthorDetailsService;
import org.optionfactory.author.Privileges;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            MvcRequestMatcher.Builder mvc,
            DaoAuthenticationProvider daoAuthenticationProvider
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/public/login")
                        .defaultSuccessUrl("/private/rooms")
                        .permitAll())
                .authenticationProvider(daoAuthenticationProvider)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/public/**")).permitAll()
                        .requestMatchers(mvc.pattern("/api/v1/author/create")).permitAll()
                        .requestMatchers(mvc.pattern("/private/**")).hasAuthority(Privileges.USER.name())
                        .requestMatchers(mvc.pattern("/admin/**")).hasAuthority(Privileges.ADMIN.name())
                        .anyRequest().permitAll()
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
    public DaoAuthenticationProvider authenticationProvider(AuthorDetailsService authorDetailsService, PasswordEncoder passwordEncoder) {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(authorDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ADMIN > USER");
        return roleHierarchy;
    }
}

