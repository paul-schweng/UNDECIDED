package cyou.ted2.undecided.tools;

import com.google.gson.Gson;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

public class Tools {

    /**
     *
     * @param min inclusive
     * @param max inclusive
     * @return random number between min and max
     */
    public static int randomInt(int min, int max){
        return (int)(Math.random() * ((max - min) + 1) + min);
    }

    public static <T> T jsonToObject(String text, Class<T> object) {
        Gson gson = new Gson();
        return gson.fromJson(text, object);
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0,16);
    }

}
