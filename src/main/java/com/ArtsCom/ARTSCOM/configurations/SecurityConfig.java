package com.ArtsCom.ARTSCOM.configurations;

import com.ArtsCom.ARTSCOM.models.enums.Role;
import com.ArtsCom.ARTSCOM.services.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService userDetailService;

    @Bean
    public DefaultSecurityFilterChain httpSecurity(HttpSecurity https) throws Exception {
        https.
                authorizeHttpRequests((request) -> request
                .requestMatchers("/","/image/**","/post/**","/registration","/static/**").permitAll().
                        anyRequest()
                .authenticated()
                )
                .formLogin((login) -> login.
                        loginPage("/login")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);

        return https.build();
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
