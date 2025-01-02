package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.example",
        "com.queryexec.utilities.repository",
        "com.queryexec.utilities.model",
        "com.queryexec.utilities",
        "com.queryexec.utilities.config"
        // Add this line to include the security config package

})
@EnableJpaRepositories(basePackages = "com.queryexec.utilities.repository")
@EntityScan(basePackages = "com.queryexec.utilities.model")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}