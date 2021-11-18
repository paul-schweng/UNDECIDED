package cyou.ted2.undecided.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;



@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class AuthenticationController extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(cyou.ted2.undecided.UndecidedApplication.class, args);
    }


    @RequestMapping("/auth/register")
    public Map<String,Object> home() throws InterruptedException {
        Map<String,Object> model = new HashMap<>();
        model.put("success", true);
        return model;
    }

    @RequestMapping("/auth/login")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }




}