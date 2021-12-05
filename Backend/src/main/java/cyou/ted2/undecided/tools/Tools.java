package cyou.ted2.undecided.tools;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tools {

    public static String getTextFromFile(String file) {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                if((line = br.readLine()) != null)
                    sb.append(System.lineSeparator());

            }
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T jsonToObject(String text, Class<T> object) {
        Gson gson = new Gson();
        return gson.fromJson(text, object);
    }

    //for testing
    public static void main(String[] args) {
        DatabaseCredentials credentials = jsonToObject("{\n" +
                                                            "  \"username\": \"paul\",\n" +
                                                            "  \"password\": \"xxxxxxxx\"\n" +
                                                            "}", DatabaseCredentials.class);
        System.out.println(credentials.username);
    }

}
