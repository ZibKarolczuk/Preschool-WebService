package pl.coderslab.preschool_web_service.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.preschool_web_service.entity.Child;
import pl.coderslab.preschool_web_service.entity.ChildGroup;
import pl.coderslab.preschool_web_service.entity.Message;
import pl.coderslab.preschool_web_service.entity.UserDetails;
import pl.coderslab.preschool_web_service.entity.security.User;
import pl.coderslab.preschool_web_service.repository.ChildGroupRepository;
import pl.coderslab.preschool_web_service.repository.ChildRepository;
import pl.coderslab.preschool_web_service.repository.MessageRepository;
import pl.coderslab.preschool_web_service.repository.UserDetailsRepository;
import pl.coderslab.preschool_web_service.repository.security.UserRepository;
import pl.coderslab.preschool_web_service.service.EmailMessage;

import javax.mail.internet.AddressException;
import javax.servlet.ServletRequest;
import javax.swing.event.ListDataEvent;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"user", "userDetails"})
public class AdminController {

//    DEPENDENCY INJECTIONS:

    @Autowired
    private UserRepository ur;

    @Autowired
    private UserDetailsRepository udr;

    @Autowired
    private ChildRepository chr;

    @Autowired
    private ChildGroupRepository cgr;

    @Autowired
    private MessageRepository mr;

//    SESSION & MODEL ATTRIBUTES:

    @ModelAttribute("childList")
    public List<Child> childList() {
        return this.chr.findAllChilds();
    }

    @ModelAttribute("groups")
    public List<ChildGroup> childGroupList() {
        return this.cgr.findAll();
    }

    @ModelAttribute("userList")
    public List<User> userList() {
        return this.ur.findAll();
    }

    @ModelAttribute("userDetailsList")
    public List<UserDetails> userDetailsList() {
        return this.udr.getAllUsersButNotAdmin();
    }

    @ModelAttribute("childGroupList")
    public List<ChildGroup> childGroupList2() {
        return this.cgr.listGroupsSorted();
    }

    @ModelAttribute("sortedChilds")
    public List<Child> childListSorted() {
        return this.chr.findAllByChildOrderBySurname();
    }

//    VIEWS CONTROLLER:

    @GetMapping("")
    public String admin() {
        return "admin/main";
    }

    @GetMapping("/child")
    public String viewChild(Model model) {
        model.addAttribute("childList", this.chr.findAll());
        return "admin/view_child";
    }

    @PostMapping("/child")
    public String viewchildsPost(Model model, @RequestParam long childId, @RequestParam long groupId) {
        Child child = this.chr.findOne(childId);
        child.setChildGroup(this.cgr.findOne(groupId));
        this.chr.save(child);
        return "redirect:/admin/child";
    }

    @GetMapping("/group")
    public String viewGroup(Model model, @ModelAttribute User user) {
        return "admin/view_group";
    }

    @PostMapping("/group")
    public String childsPost(Model model, @RequestParam long childId, @RequestParam long groupId) {
        Child child = this.chr.findOne(childId);
        child.setChildGroup(this.cgr.findOne(groupId));
        this.chr.save(child);
        return "redirect:/admin/group";
    }

    @GetMapping("/group/new")
    public String groupNew(Model model) {
        model.addAttribute("childGroup", new ChildGroup());
        model.addAttribute("title", "Dodaj nową grupę");
        return "form/new_group";
    }

    @PostMapping("/group/new")
    public String groupNewPost(@Valid ChildGroup childGroup,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "form/new_group";
        }

        String groupName = childGroup.getGroupName().trim().toLowerCase();

        if (this.cgr.listGroupNameSortedToLowerCase().contains(groupName)) {
            return "form/new_group";
        }

        childGroup.setGroupName(groupName.substring(0, 1).toUpperCase() + childGroup.getGroupName().substring(1).toLowerCase());
        this.cgr.save(childGroup);
        return "redirect:/admin/group";
    }

    @GetMapping("/user")
    public String viewUser(Model model, @ModelAttribute UserDetails userDetails) {
        model.addAttribute("childs", this.chr.findAllChilds());
        return "admin/view_user";
    }

    @GetMapping("/user/email/{userId}")
    public String userSendEmail(@PathVariable long userId, Model model, @ModelAttribute UserDetails userDetails) {
        model.addAttribute("message", new Message());

        String title = "";
        if (this.udr.findOne(userId).getName() != null || this.udr.findOne(userId).getSurname() != null) {
            title = this.udr.findOne(userId).getName() + " " + this.udr.findOne(userId).getSurname();
        } else {
            title = this.udr.findOne(userId).getEmail();
        }
        if (this.udr.findOne(userId).getEmail2() != null){
            if (!this.udr.findOne(userId).getName2().isEmpty() || !this.udr.findOne(userId).getSurname2().isEmpty()) {
                title += " oraz " + this.udr.findOne(userId).getName2() + " " + this.udr.findOne(userId).getSurname2();
            }
        }

        model.addAttribute("title", title);

        return "message/adminToUser";
    }

    @PostMapping("/user/email/{userId}")
    public String userSendEmailPost(@Valid Message message,
                                    BindingResult result,
                                    @PathVariable long userId,
                                    ServletRequest servletRequest) throws EmailException, AddressException, NullPointerException {
        if (result.hasErrors()) {
            return "message/adminToUser";
        }

        LocalDateTime timeStamp = LocalDateTime.now();

        message.setSendFrom("admin");
        message.setDateTime(timeStamp.toString());

        ArrayList<String> emailList = new ArrayList<>();
        String email = this.ur.findOne(userId).getEmail();
        emailList.add(email);
        if (this.udr.getEmail2ByEmail(email).length()>0) emailList.add(this.udr.getEmail2ByEmail(email));
        EmailMessage emailMessage = new EmailMessage(servletRequest, emailList, "Stacyjkowo Admin", message);

        message.setSendTo(emailList.toString());
        this.mr.save(message);

        return "redirect:/admin/user";
    }

    @GetMapping("/group/{id}/edit")
    public String groupEdit(@PathVariable long id,
                            Model model) {
        model.addAttribute("childGroup", this.cgr.findOne(id));
        model.addAttribute("title", "Edytuj grupę " + this.cgr.findOne(id).getGroupName());
        return "form/new_group";
    }

    @PostMapping("/group/{id}/edit")
    public String groupEditPost(@Valid ChildGroup childGroup,
                                BindingResult result,
                                Model model,
                                @ModelAttribute User user,
                                @PathVariable long id) {
        if (result.hasErrors()) {
            return "form/new_group";
        }

        String groupName = childGroup.getGroupName().trim().toLowerCase();

        if (this.cgr.listGroupNameSortedToLowerCase().contains(groupName) &&
                !this.cgr.findOne(id).getGroupName().toLowerCase().equals(groupName)) {
            return "form/new_group";
        }

        childGroup.setGroupName(groupName.substring(0, 1).toUpperCase() + childGroup.getGroupName().substring(1).toLowerCase());
        this.cgr.save(childGroup);
        return "redirect:/admin/group";
    }

    @GetMapping("/group/{id}/delete")
    public String removeGroup(@PathVariable long id,
                              Model model, @ModelAttribute User user) {
        this.cgr.delete(id);
        return "redirect:/admin/group";
    }

    @GetMapping("/group/{groupId}/message")
    public String groupsMessage(Model model, @PathVariable long groupId) {
        model.addAttribute("message", new Message());
        model.addAttribute("groupSelected", groupId);
        return "message/adminToGroup";
    }

    @PostMapping("/group/{groupId}/message")
    public String groupsMessagePost(@Valid Message message, BindingResult result,
                                    @PathVariable long groupId, Model model,
                                    @ModelAttribute User user,
                                    @RequestParam List<String> groupTo,
                                    ServletRequest servletRequest) throws EmailException, AddressException {

        groupTo.remove("0");
        if (result.hasErrors() || groupTo.size() == 0) {
            model.addAttribute("groupSelected", groupId);
            return "message/adminToGroup";
        }

        LocalDateTime timeStamp = LocalDateTime.now();

        message.setSendFrom("admin");
        message.setDateTime(timeStamp.toString());

        ArrayList<String> emailList = new ArrayList<>();
        ArrayList<String> groupListName = new ArrayList<>();
        for (String group : groupTo) {
            groupListName.add(this.cgr.findOne(Long.parseLong(group)).getGroupName());
            for (String email : this.chr.getUserEmailByChildGroup(group)) {
                if (!emailList.contains(email)) {
                    emailList.add(email);
                    if (this.udr.getEmail2ByEmail(email) != null) emailList.add(this.udr.getEmail2ByEmail(email));
                }

            }
        }
        EmailMessage emailMessage = new EmailMessage(servletRequest, emailList, "Stacyjkowo New Post", message);
        message.setSendTo(groupListName.toString());
        this.mr.save(message);
        return "redirect:/admin/group";
    }

    @GetMapping("/user/delete/{userId}")
    @Transactional
    public String removeUser(@PathVariable long userId) {
        for (Child child : this.chr.getAllChildsByUser(userId)) {
            Long childId = child.getId();
            child.setChildGroup(null);
            this.chr.delete(childId);
        }
        this.ur.delete(userId);
        return "redirect:/admin/user";
    }

}
