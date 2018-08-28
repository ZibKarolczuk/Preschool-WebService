package pl.coderslab.preschool_web_service.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private @NotBlank
    String name;
    private @NotBlank String surname;
    private @NotBlank String phone;

    private @NotBlank String addressStreet;
    private String addressPostCode;
    private @NotBlank String addressCity;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public UserDetails() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstPersonName) {
        this.name = firstPersonName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String firstPersonSurname) {
        this.surname = firstPersonSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String firstPersonPhone) {
        this.phone = firstPersonPhone;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressPostCode() {
        return addressPostCode;
    }

    public void setAddressPostCode(String addressPostCode) {
        this.addressPostCode = addressPostCode;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
