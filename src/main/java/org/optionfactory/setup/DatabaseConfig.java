package org.optionfactory.setup;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.optionfactory.messages.JdbcMessageRepository;
import org.optionfactory.messages.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(
            @Value("${db.jdbc.url}") String jdbcUrl,
            @Value("${db.username}") String username,
            @Value("${db.password}") String password
    ) throws PropertyVetoException {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setProperties(new Properties());
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(org.postgresql.Driver.class.getName());
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setInitialPoolSize(5);
        dataSource.setMaxPoolSize(50);
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(1);
        dataSource.setAcquireRetryAttempts(3);
        dataSource.setMaxIdleTime(60);
        dataSource.setPreferredTestQuery("select 1");
        dataSource.setTestConnectionOnCheckout(true);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcOperations jdbc(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public MessageRepository messageRepository(JdbcOperations jdbcOperations) {
        return new JdbcMessageRepository(jdbcOperations);
    }
}
