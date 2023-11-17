package com.cleverpine.template.config;

import com.cleverpine.viravaspringhelper.filter.ViravaFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final ViravaFilter authoritiesFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests ->
                requests.requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()));

        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAfter(authoritiesFilter, BasicAuthenticationFilter.class)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
