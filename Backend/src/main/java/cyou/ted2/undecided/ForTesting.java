package cyou.ted2.undecided;

import cyou.ted2.undecided.tools.PasswordHashing;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ForTesting {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(PasswordHashing.getHash("5juYhCT8QFh6Mr6"));

        long x = Math.round(4.499999999999999999999999999999999999999999999);
        LinkedHashMap LinkedHashMap = new LinkedHashMap<>();
        System.out.println(LinkedHashMap instanceof Map);


        System.out.println(Boolean.parseBoolean("1"));
    }
}
