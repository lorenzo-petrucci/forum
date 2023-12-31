package org.optionfactory.setup;

import org.optionfactory.author.AuthorDetailsService;
import org.optionfactory.author.PrivilegeType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
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
                .formLogin(form -> form
                        .loginPage("/public/login")
                        .defaultSuccessUrl("/private/rooms")
                        .permitAll())
                .authenticationProvider(daoAuthenticationProvider)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/")).permitAll()
                        .requestMatchers(mvc.pattern("/public/**")).permitAll()
                        .requestMatchers(mvc.pattern("/assets/public/**")).permitAll()
                        .requestMatchers(mvc.pattern("/private/**")).hasAuthority(PrivilegeType.USER.name())
                        .requestMatchers(mvc.pattern("/admin/**")).hasAuthority(PrivilegeType.ADMIN.name())
                        .anyRequest().authenticated()
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

