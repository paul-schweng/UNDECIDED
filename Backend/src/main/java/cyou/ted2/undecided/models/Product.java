package cyou.ted2.undecided.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name, brand, description, officialImage;
    @ElementCollection
    private List<String> types;
    @ElementCollection
    private List<String> labels;
    private boolean verified;
}
