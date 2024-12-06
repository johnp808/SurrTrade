package com.surrtrade.security;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	// this you get for free when you configure the db connection in application.properties file
    @Autowired
    private DataSource dataSource;

    // this bean is created in the application starter class
    @Autowired
    private PasswordEncoder encoder;

    @Bean
    SecurityFilterChain createFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(withDefaults());  // Use HTTP Basic Authentication
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()    // will hit the OPTIONS on the route
            .requestMatchers("/api/authenticate").permitAll()         // Allow access to authenticate endpoint
            .requestMatchers("/api/checkusername/**").permitAll()
            .requestMatchers("/api/checkemail/**").permitAll()
            .requestMatchers("/api/register/**").permitAll()         // Allow access to authenticate endpoint
            .anyRequest().authenticated());
        http.sessionManagement(management -> management
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "SELECT username, password, enabled FROM user WHERE username=?";
        String authQuery = "SELECT username, role FROM user WHERE username=?";
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(userQuery)
            .authoritiesByUsernameQuery(authQuery)
            .passwordEncoder(encoder);
    }
}

