package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class AuthenticationController extends SpringBootServletInitializer {


    private UserRepository userRepository;


    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/auth/register")
    public Map<String, Boolean> register(@RequestBody User user) throws InterruptedException {
        var map = new HashMap<String, Boolean>();
        if(userRepository.findUserByUsername(user.getUsername()) != null)
            map.put("usernameError", true);
        else if(userRepository.findUserByEmail(user.getEmail()) != null)
            map.put("emailError", true);
        else
            userRepository.save(user);
        return map;
    }

    @GetMapping("/auth/login")
    public Principal user(Principal user) {
        System.out.println(user + "login");
        return user;
    }

    @GetMapping("/auth/user-available")
    public Map<String, Boolean> existsUsername(@RequestParam String u) {
        var map = new HashMap<String, Boolean>();
        map.put("available", userRepository.findUserByUsername(u) == null);
        return map;
    }




}