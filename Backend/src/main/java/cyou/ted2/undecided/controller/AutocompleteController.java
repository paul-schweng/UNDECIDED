package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/autocomplete")
public class AutocompleteController {

    public AutocompleteController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @PostMapping("/friend")
    List<User> getFriends(@RequestBody Autocomplete autocomplete) {
        System.out.println(autocomplete.input);
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        List<User> users = userRepository.findByUsernameContainingOrNameContaining(autocomplete.input, autocomplete.input); //TODO: remove critical data (email...)

        users.removeIf(u -> Objects.equals(u.getId(), userId));
        int usersNum = 11;
        int toIndex = Math.min(users.size(), usersNum);
        return users.subList(0, toIndex);
    }

    @PostMapping("/type")
    void getTypes(@RequestBody Autocomplete autocomplete) {
        System.out.println(autocomplete.input);
    }

    @PostMapping("/brand")
    void getBrands(@RequestBody Autocomplete autocomplete) {
        System.out.println(autocomplete.input);
    }

    @PostMapping("/product")
    void getProducts(@RequestBody Autocomplete autocomplete) {
        System.out.println(autocomplete.input);
    }
}

class Autocomplete {
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    String input;
}
