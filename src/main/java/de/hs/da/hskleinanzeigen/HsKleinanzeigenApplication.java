package de.hs.da.hskleinanzeigen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;

@EnableJpaAuditing
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Controller
public class HsKleinanzeigenApplication {
    public static void main(String[] args) {
        SpringApplication.run(HsKleinanzeigenApplication.class, args);
    }
}
