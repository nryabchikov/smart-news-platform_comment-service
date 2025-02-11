package ru.clevertec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/api/v1/news/{newsId}/comments").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/news/{newsId}/comments/{commentsId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/news/{newsId}/comments")
                        .hasAuthority("SCOPE_edit_comment")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/news/{newsId}/comments")
                        .hasAuthority("SCOPE_edit_comment")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/news/{newsId}/comments/{commentId}")
                        .hasAuthority("SCOPE_edit_comment")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/news/{newsId}/comments")
                        .hasAuthority("SCOPE_edit_comment")
                        .anyRequest().denyAll())
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        .jwt(Customizer.withDefaults()))
                .build();
    }
}
