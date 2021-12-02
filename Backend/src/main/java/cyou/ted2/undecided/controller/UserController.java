package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

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

    /*@PutMapping("/password")
    @ResponseBody
    User putNewEmail(@RequestBody String currentPw, String newPw) {
        User user = repository.findUserByPassword(currentPw); //TODO: userzuordnung?
        user.setPassword(newPw);
        return repository.save(user);
    }*/

    @GetMapping()
    @ResponseBody
    User getUser(){return null;}//TODO

    @PutMapping()
    @ResponseBody
    User putUpdateUser(){return null;}//TODO








}
