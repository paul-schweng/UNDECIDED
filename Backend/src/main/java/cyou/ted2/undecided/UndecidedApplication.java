package cyou.ted2.undecided;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class UndecidedApplication {

    public static void main(String[] args) {
        SpringApplication.run(UndecidedApplication.class, args);
    }

    @RequestMapping("/api/greeting")
    public Map<String,Object> home() throws InterruptedException {
        Map<String,Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello Paul");
        Thread.sleep(5000);
        return model;
    }

}
