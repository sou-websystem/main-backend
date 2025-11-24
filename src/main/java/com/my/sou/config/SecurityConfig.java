package com.my.sou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.my.sou.service.SouAuthenticationProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Autowired
  private CustomAuthEntryPoint customAuthEntryPoint;

  private final JwtFilter jwtFilter;

  public SecurityConfig(final JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
      SouAuthenticationProvider souAuthenticationProvider) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/public/auth/login").permitAll().requestMatchers("/public/**")
            .permitAll().requestMatchers("/error").permitAll().anyRequest().authenticated())
        .authenticationProvider(souAuthenticationProvider)
        .exceptionHandling(ex -> ex.defaultAuthenticationEntryPointFor(customAuthEntryPoint,
            request -> request.getRequestURI().equals("/public/auth/login")
                && request.getMethod().equals("POST")))
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));;

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

}
