package home.transaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({JdbcConfig.class,TransactionConfig.class})
@EnableTransactionManagement
public class ConfigRuation {
}
