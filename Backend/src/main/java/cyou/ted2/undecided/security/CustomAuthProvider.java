package cyou.ted2.undecided.security;

import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.tools.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Objects;

@Component
public class CustomAuthProvider implements AuthenticationProvider {


    @Autowired
    private UserRepository usersRepository;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("\n \n \n");
        System.out.println(authentication);
        System.out.println("\n \n \n");


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
                System.out.println("\nuser not found\n");

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