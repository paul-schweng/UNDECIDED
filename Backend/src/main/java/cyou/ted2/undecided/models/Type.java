package cyou.ted2.undecided.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
public class Type {

    int count;
    @Id
    String label;
    static List<Type> types;
}
