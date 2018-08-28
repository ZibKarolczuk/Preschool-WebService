package pl.coderslab.preschool_web_service.entity;

import org.hibernate.validator.constraints.NotBlank;
import pl.coderslab.preschool_web_service.entity.security.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotBlank String name;
    private @NotBlank String surname;
    private LocalDate birthhday;

    private String specialInfoDiet;
    private String specialInfoMedication;
    private String specialInfoOther;
    private String specialAllowedPickUp;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private UserDetails userDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    private ChildGroup childGroup;

    public Child() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthhday() {
        return birthhday;
    }

    public void setBirthhday(LocalDate birthhday) {
        this.birthhday = birthhday;
    }

    public String getSpecialInfoDiet() {
        return specialInfoDiet;
    }

    public void setSpecialInfoDiet(String specialInfoDiet) {
        this.specialInfoDiet = specialInfoDiet;
    }

    public String getSpecialInfoMedication() {
        return specialInfoMedication;
    }

    public String getSpecialAllowedPickUp() {
        return specialAllowedPickUp;
    }

    public void setSpecialAllowedPickUp(String specialAllowedPickUp) {
        this.specialAllowedPickUp = specialAllowedPickUp;
    }

    public void setSpecialInfoMedication(String specialInfoMedication) {
        this.specialInfoMedication = specialInfoMedication;
    }

    public String getSpecialInfoOther() {
        return specialInfoOther;
    }

    public void setSpecialInfoOther(String specialInfoOther) {
        this.specialInfoOther = specialInfoOther;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public ChildGroup getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(ChildGroup childGroup) {
        this.childGroup = childGroup;
    }
}
