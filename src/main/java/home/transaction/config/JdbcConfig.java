package home.transaction.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author wb-gwj607956
 * @version $Id: JdbcConfig.java, v 0.1 2020年12月16日 11:07 wb-gwj607956 Exp $
 */
@Configuration
public class JdbcConfig {

    @Value("${driverClass}")
    private String driverClass;

    @Value("${jdbcUrl}")
    private String jdbcUrl;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Bean(value = "dataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        DataSource dataSource = new ComboPooledDataSource();
        try {
            ((ComboPooledDataSource) dataSource).setDriverClass(driverClass);
            ((ComboPooledDataSource) dataSource).setJdbcUrl(jdbcUrl);
            ((ComboPooledDataSource) dataSource).setUser(user);
            ((ComboPooledDataSource) dataSource).setPassword(password);
            return dataSource;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Bean(value = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        System.out.println("JdbcConfig-dataSource \t" + dataSource);
        return new JdbcTemplate(dataSource);
    }

    @Bean(value = "queryRunner")
    public QueryRunner getQueryRunner(DataSource dataSource) {
        QueryRunner query = new QueryRunner(dataSource);
        return query;
    }
}