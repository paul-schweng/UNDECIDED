package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.tools.PasswordHashing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/api/user")
class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/email")
    @ResponseBody
    User postNewEmail(@RequestBody String currentEmail, String newEmail) {
        User user = repository.findUserByEmail(currentEmail);
        user.setEmail(newEmail);
        return repository.save(user);
    }

    @PutMapping("/password")
    @ResponseBody
    User putNewPassword(@RequestBody String currentPw, String newPw) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = repository.findUserById(userId);
        if (PasswordHashing.hashPassword(currentPw).equals(user.getPassword())){
            user.setPassword(PasswordHashing.hashPassword(newPw));
            return repository.save(user);
        }
        return null; //later replace with exception
    }

    @GetMapping()
    @ResponseBody
    User getUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return repository.findUserById(userId);
    }

    @PutMapping()
    @ResponseBody
    User putUpdateUser(@RequestBody User u) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = repository.findUserById(userId);
        user.update(u);
        return repository.save(user);
    }

}
