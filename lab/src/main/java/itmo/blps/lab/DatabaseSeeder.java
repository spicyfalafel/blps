package itmo.blps.lab;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
@Component
public class DatabaseSeeder {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DatabaseSeeder.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertData() {
        LOGGER.info("> Inserting data...");
        jdbcTemplate.execute("INSERT INTO medication(title,description) VALUES ('med1', 'med1 decs')");
    }
}