package pl.edu.pja.gden.tpopros30182.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/home", "/home/**").permitAll()
                        .requestMatchers("/register", "/register*", "/registration-confirm").permitAll()
                        .requestMatchers("/user", "/user**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin", "/admin**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home/catalogue", true)
                        .permitAll()
                );// Enable in production

        return http.build();
    }
}
