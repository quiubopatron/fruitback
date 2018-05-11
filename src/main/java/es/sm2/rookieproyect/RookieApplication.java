package es.sm2.rookieproyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RookieApplication {

    public static void main(String[] args) {
        SpringApplication.run(RookieApplication.class, args);
    }
}
