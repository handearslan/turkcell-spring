package com.turkcell.spring.workshop.core.configurations;

import com.turkcell.spring.workshop.core.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    //filtrelerin geleceği yer
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    //bu url lerde giriş yapma zorunluluğu arama
    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/api/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
    };

    //filtre lerin zincirinin tanımlanması
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // listedeki url'lerin auth zorunluluğu olmaması
        http
                .csrf(AbstractHttpConfigurer::disable)//güvenlik (zaafiyet önleme gibi)
                .authorizeHttpRequests(req -> req           //hepsini onayla
                        .requestMatchers(WHITE_LIST_URLS).permitAll()//listedeki url lerin authenticate zorunluluğu yoktur.
                        .requestMatchers(HttpMethod.GET,"/categories/**").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers(HttpMethod.POST,"/categories/**").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET,"/products/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/products/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated())//her request authenticate olmalı...
                .authenticationProvider(authenticationProvider)//uthentication işlemlerinde Provider ı kullan
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//jwt kullanacağız session ile bir işimiz yok şuan
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//öncesinde kontrol edilmesi gereken filtreler
        return http.build();
    }



}
