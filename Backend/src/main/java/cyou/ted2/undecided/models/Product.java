package cyou.ted2.undecided.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "productid")
    private Long id;
    private String name, brand, description, officialImage;
    @ElementCollection
    private List<String> types;
    @ElementCollection
    private List<String> labels;
    private boolean verified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfficialImage() {
        return officialImage;
    }

    public void setOfficialImage(String officialImage) {
        this.officialImage = officialImage;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    //TODO: remove this, just for testing
    public Product(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public Product() { }
}
