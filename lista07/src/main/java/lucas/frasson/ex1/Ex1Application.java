package lucas.frasson.ex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "lucas.frasson")
public class Ex1Application {
    public static void main(String[] args) {
        SpringApplication.run(Ex1Application.class, args);
    }
}