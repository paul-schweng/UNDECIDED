package cyou.ted2.undecided;

import com.google.gson.Gson;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.AuthRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.tools.PasswordHashing;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;


@SpringBootApplication( /*exclude = SecurityAutoConfiguration.class */)
@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class UndecidedApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UndecidedApplication.class, args);
    }

}
