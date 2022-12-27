package com.example.springsecuritypresentation.security;

import com.example.springsecuritypresentation.security.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Locale;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth ->{

                    auth.requestMatchers("/h2console","api/user/reqister").permitAll();
                    auth.requestMatchers("/api/user/any-of-request-login")
                            .hasAnyAuthority(
                                    Authority.ADMIN.getAuthority().toUpperCase(Locale.ENGLISH),
                                    Authority.USER.getAuthority().toUpperCase(Locale.ENGLISH),
                                    Authority.EDITOR.getAuthority().toUpperCase(Locale.ENGLISH),
                                    Authority.READONLY.getAuthority().toUpperCase(Locale.ENGLISH));}

                )
                .csrf(AbstractHttpConfigurer::disable)
                        .formLogin(Customizer.withDefaults())
                .addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class)
                        .headers()
                                .frameOptions()
                                        .disable().and().build();



    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
