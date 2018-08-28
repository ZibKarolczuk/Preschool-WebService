package pl.coderslab.preschool_web_service.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChildGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Child> childList = new ArrayList<>();

    public ChildGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
