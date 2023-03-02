package com.daw.practica11.configuration;

import com.daw.practica11.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.requestMatchers("/", "/auth/login", "/auth/signin", "/exito").permitAll().anyRequest().authenticated()
                                .and()
                                .formLogin().loginPage("/auth/login").defaultSuccessUrl("/auth/logincheck", true)
                                .failureUrl("/auth/login?error=true").loginProcessingUrl("/auth/login/check").permitAll()
                                .and()
                                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).build();
    }


}
