package com.yueking.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringBootBaseDemoApplicationTests {
    private static String myKey = "yuekingKey";
    @Test
    void contextLoads() {
    }

    @Test
    void testJwt() {
        String token = createJwt();
        System.out.println(token);

        System.out.println("===============================");
        String[] split = token.split("\\.");
        System.out.println(split.length);
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }

    String createJwt(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("yueking")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, myKey);
        return jwtBuilder.compact();
    }
    String createJwtExp(){
        long currentDate = System.currentTimeMillis();
        long expDate = currentDate+5*1000;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("yueking")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, myKey)
                .setExpiration(new Date(expDate));
        return jwtBuilder.compact();
    }
    @Test
    void testParseJwt() throws InterruptedException {
        String token = createJwt();
        // String token = createJwtExp();
        System.out.println(token);
        // Thread.sleep(6000);
        Claims claims = (Claims) Jwts.parser().setSigningKey(myKey).parse(token).getBody();
        String id = claims.getId();
        System.out.println(id);
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
    }
}
