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

/*
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

 */

    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomAuthProvider customAuthProvider;
        @Autowired
        private UserRepository usersRepository;
        @Autowired
        private AuthRepository authRepository;

        @Component
        public class CustomAuthProvider implements AuthenticationProvider {
            @SneakyThrows
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                System.out.println("\n \n \n");
                System.out.println(authentication);
                System.out.println("\n \n \n");

                if(authentication.getName() == "#"){
                    User userFromCookie = userFromCookie();
                    return new UsernamePasswordAuthenticationToken(userFromCookie.getId(), userFromCookie.getPassword(), Collections.emptyList());
                }

                String userid = authentication.getName();
                String password = "";


                try {
                    password = authentication.getCredentials() != null ? PasswordHashing.getHash(authentication.getCredentials().toString()) : "";
                }catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                Authentication auth = null;
                System.out.println("credentials: " + userid + "  " + password);


                try {
                    if(userid.equals("admin") && password.equals(PasswordHashing.getHash("1")))
                        return new UsernamePasswordAuthenticationToken(userid, password, Collections.emptyList());

                    User user = usersRepository.findUserByEmail(userid);
                    if(user == null)
                        user = usersRepository.findUserByUsername(userid);

                    boolean userExists = user != null && Objects.equals(user.getPassword(), password);

                    if(userExists)
                        auth = new UsernamePasswordAuthenticationToken(user.getId(), password, Collections.emptyList());
                    else
                        System.out.println("user not found");

                } catch (Exception e) {
                    System.out.println("Error on authentication: " + e);
                }
                return auth;
            }


            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        }

        public User userFromCookie(){
            return authRepository.getById(/*cookie here*/ "").getUser();
        }


        @Autowired
        public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(customAuthProvider);
        }



        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                        .antMatchers("/auth/*").permitAll()
                        .anyRequest().authenticated()
                    .and()
                        .logout()
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/asdzhgföiöpugfjb")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                    .and()
                        .csrf()
                        .ignoringAntMatchers("/auth/*")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        }

    }



}
