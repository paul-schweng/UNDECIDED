package cyou.ted2.undecided;

import io.cucumber.java.en.Given;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication( /*exclude = SecurityAutoConfiguration.class */)
@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class UndecidedApplication extends SpringBootServletInitializer {

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

    @RequestMapping("/api/user")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }

    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic().and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        }
    }

}
