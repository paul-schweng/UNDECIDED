package cyou.ted2.undecided.services;

import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public class AuthorizationService {

    private UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean checkAuthorization(String user){
        return false;
    }
}
