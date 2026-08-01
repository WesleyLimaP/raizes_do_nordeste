package com.uninter.raiazesdonordeste.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class FlywayDevConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .cleanDisabled(false)
                .locations("classpath:db/migration")
                .load();

        flyway.clean();
        flyway.migrate();
    }
}
