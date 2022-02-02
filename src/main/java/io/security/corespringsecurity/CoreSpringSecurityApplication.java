package io.security.corespringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"io.security.corespringsecurity.module"})
@EnableConfigurationProperties
@EntityScan(basePackages = {"io.security.corespringsecurity.module"})
@EnableJpaRepositories(basePackages = {"io.security.corespringsecurity.module"})
@EnableJpaAuditing
public class CoreSpringSecurityApplication {

    public static void main(String[] args) {

        SpringApplication.run(CoreSpringSecurityApplication.class, args);
    }

}
