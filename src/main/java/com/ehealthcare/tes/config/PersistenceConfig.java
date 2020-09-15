package com.ehealthcare.tes.config;

import com.ehealthcare.tes.repository.AuditorAwareImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.ehealthcare.tes.domain")
@EnableJpaRepositories("com.ehealthcare.tes.repository")
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfig {

    @Bean
    AuditorAware<Integer> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
