package cyou.ted2.undecided.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String name, country, zip, city, street, houseNumber;
}
