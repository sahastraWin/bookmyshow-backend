package com.bms.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.url:}")
    private String url;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Bean
    @Primary
    public DataSource dataSource() {
        if (url != null && !url.trim().isEmpty() && !url.contains("mem:bookmyshow")) {
            try {
                log.info("Testing connection to configured database: {}", url);
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(url);
                config.setUsername(username);
                config.setPassword(password);
                config.setInitializationFailTimeout(3000);
                config.setConnectionTimeout(3000);
                
                HikariDataSource ds = new HikariDataSource(config);
                try (Connection conn = ds.getConnection()) {
                    log.info("Database connection verified successfully.");
                    return ds;
                }
            } catch (Exception e) {
                log.warn("Configured database at '{}' is unreachable ({}). Falling back to embedded in-memory database for cloud demo.", url, e.getMessage());
            }
        }

        log.info("Starting with embedded in-memory database (MySQL compatibility mode)...");
        HikariConfig h2Config = new HikariConfig();
        h2Config.setJdbcUrl("jdbc:h2:mem:bookmyshow;DB_CLOSE_DELAY=-1;MODE=MySQL;NON_KEYWORDS=USER");
        h2Config.setUsername("sa");
        h2Config.setPassword("");
        h2Config.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(h2Config);
    }
}
