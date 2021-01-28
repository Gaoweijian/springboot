package home.transaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JdbcConfig.class,TransactionConfig.class})
public class ConfigRuation {
}
