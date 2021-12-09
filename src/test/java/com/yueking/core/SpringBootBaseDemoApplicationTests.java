package com.yueking.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringBootBaseDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void password() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "yueking";
        String encode = passwordEncoder.encode(password);
        boolean matches = passwordEncoder.matches(password, encode);
        System.out.println(password);
        System.out.println(encode);
        System.out.println(matches);

    }

}
