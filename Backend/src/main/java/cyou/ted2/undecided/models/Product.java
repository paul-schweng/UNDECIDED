package cyou.ted2.undecided.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import cyou.ted2.undecided.providers.MyGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "cyou.ted2.undecided.providers.MyGenerator")
    @Column(name = "productid", nullable = false)
    private String id;
    private String name, brand, description, officialImage;

    @OneToMany
    private List<Type> types;

    @JsonInclude
    @Transient
    private List<Integer> labels;

    private String labelList;

    private boolean verified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Product() { }

    public String getLabelList() {
        return labelList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }
}
