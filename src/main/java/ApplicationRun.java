import home.transaction.config.ConfigRuation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(value = "home")
@Import(ConfigRuation.class)
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class);
    }
}