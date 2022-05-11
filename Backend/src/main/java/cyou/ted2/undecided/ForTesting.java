package cyou.ted2.undecided;

import cyou.ted2.undecided.tools.PasswordHashing;

import java.security.NoSuchAlgorithmException;

public class ForTesting {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(PasswordHashing.getHash("5juYhCT8QFh6Mr6"));
    }
}
