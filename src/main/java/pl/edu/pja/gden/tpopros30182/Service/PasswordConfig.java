package pl.edu.pja.gden.tpopros30182.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        return new DelegatingPasswordEncoder("bcrypt", encoderMap());
    }

    @Bean
    public Map<String, PasswordEncoder> encoderMap() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put("bcrypt", new BCryptPasswordEncoder());

        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder(
                "", 1, 10,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256
        ));

        encoders.put("scrypt", new SCryptPasswordEncoder(
                16384, 8, 1, 32, 64
        ));

        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());

        return encoders;
    }

    public static List<String> encoderIds() {
        return List.of("bcrypt", "pbkdf2", "scrypt", "argon2");
    }
}
