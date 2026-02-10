package com.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class Securityconfig {
    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity){
        httpsecurity
                .csrf((csrf)-> csrf.disable())
                .authorizeHttpRequests((auth) ->auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
        return httpsecurity.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }

}
