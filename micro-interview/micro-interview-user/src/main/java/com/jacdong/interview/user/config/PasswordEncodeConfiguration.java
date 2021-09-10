package com.jacdong.interview.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @ClassName: PasswordEncodeConfiguration
 * @Description: TODO
 * @author DongBin
 * @date 2021-09-09 10:54:42
 */
@Configuration
public class PasswordEncodeConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
