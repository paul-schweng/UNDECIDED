package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Authentication;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.AuthRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*", maxAge=3600)
public class AuthenticationController extends SpringBootServletInitializer {
//

    private UserRepository userRepository;
    private AuthRepository authRepository;

    public AuthenticationController(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    @PostMapping("/register")
    public Map<String, Boolean> register(@RequestBody User user) throws InterruptedException {
        var map = new HashMap<String, Boolean>();
        if(userRepository.findUserByUsername(user.getUsername()) != null)
            map.put("usernameError", true);
        else if(userRepository.findUserByEmail(user.getEmail()) != null)
            map.put("emailError", true);
        else {
            user.setRegisterDate(ZonedDateTime.now());
            userRepository.save(user);
        }
        return map;
    }

    @GetMapping("/login")
    public Principal user(Principal user) {
        System.out.println(user + "login");
        return user;
    }

    @GetMapping("/user-available")
    public Map<String, Boolean> existsUsername(@RequestParam String u) {
        var map = new HashMap<String, Boolean>();
        map.put("available", userRepository.findUserByUsername(u) == null);
        return map;
    }

    @GetMapping("/rememberMe")
    public ResponseEntity<?> rememberMe(@CookieValue("JSESSIONID") String session){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = new User();
        user.setId(userId);

        Authentication authentication = new Authentication();
        authentication.setSession(session);
        authentication.setUser(user);

        authRepository.save(authentication);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
