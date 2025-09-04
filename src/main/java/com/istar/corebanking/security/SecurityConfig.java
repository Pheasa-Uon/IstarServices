package com.istar.corebanking.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    public SecurityConfig(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtils, userDetailsService);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ ENABLE CORS
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // user auth login and logout
                        .requestMatchers("/api/coregateways/authentication/**").permitAll()
                        .requestMatchers("/api/coregateways/authentication/login").permitAll()
                        .requestMatchers("/api/coregateways/authentication/logout").permitAll()
                        .requestMatchers("/api/coregateways/permissions/menus/**").authenticated()
                        .requestMatchers("/api/coregateways/permissions/feature/**").authenticated()
                        .requestMatchers("/api/coregateways/permissions/reports/**").authenticated()

                        // user
                        .requestMatchers("/api/coregateways/users/**").permitAll()
                        .requestMatchers("/api/coregateways/users/status").permitAll()
                        .requestMatchers("/api/coregateways/users/search").permitAll()
                        .requestMatchers("/api/coregateways/users/*/reset-password").permitAll()
                        .requestMatchers("/api/coregateways/userroles/**").permitAll()
                        .requestMatchers("/api/coregateways/userroles/assign/**").permitAll()
                        .requestMatchers("/api/coregateways/userroles/remove/**").permitAll()

                        //role permission
                        .requestMatchers("/api/coregateways/roles/status").permitAll()
                        .requestMatchers("/api/coregateways/roles/**").permitAll()
                        .requestMatchers("/api/coregateways/roles/search/**").permitAll()

                        .requestMatchers("/api/coregateways/rolepermissions/**").permitAll()
                        .requestMatchers("/api/coregateways/rolepermissions/bulk").permitAll()
                        .requestMatchers("/api/coregateways/rolepermissions/role/**").permitAll()

                        .requestMatchers("/api/coregateways/features/treetable/**").permitAll()

                        .requestMatchers("/api/coregateways/mainmenu/**").permitAll()
                        .requestMatchers("/api/coregateways/mainmenu/treetable/**").permitAll()

                        .requestMatchers("/api/coregateways/reports/**").permitAll()
                        .requestMatchers("/api/coregateways/reports/treetable/**").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler(401);
    }
}
