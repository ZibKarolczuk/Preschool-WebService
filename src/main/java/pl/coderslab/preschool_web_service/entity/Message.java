package pl.coderslab.preschool_web_service.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.preschool_web_service.entity.security.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Email
    private String sendFrom;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
//    private List<User> User;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    public Message() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String get$sendToList() {
        return null;
    }
}
