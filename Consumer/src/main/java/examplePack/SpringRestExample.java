package examplePack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestExample {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringRestExample.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
