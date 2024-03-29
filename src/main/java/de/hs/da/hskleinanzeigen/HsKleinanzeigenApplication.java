package de.hs.da.hskleinanzeigen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Controller
@EnableCaching
public class HsKleinanzeigenApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsKleinanzeigenApplication.class, args);
    }
}
