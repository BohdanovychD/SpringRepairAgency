package spring.agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class SpringRepairAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRepairAgencyApplication.class, args);
    }
}
