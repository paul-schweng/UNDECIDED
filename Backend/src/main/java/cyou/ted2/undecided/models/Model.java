package cyou.ted2.undecided.models;

import java.lang.reflect.Field;

public class Model {

    public <T> void update(T object) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                if(field.get(object) != null ){
                    field.set(this, field.get(object));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
