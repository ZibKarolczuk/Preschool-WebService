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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    @Autowired
    private UserRepository ur;

    @Autowired
    private ChildRepository chr;

    @Autowired
    private ChildGroupRepository cgr;

    @Autowired
    private MessageRepository mr;

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

    @ModelAttribute("childGroupList")
    public List<ChildGroup> childGroupList2() {
        return this.cgr.listGroupsSorted();
    }

    @ModelAttribute("sortedChilds")
    public List<Child> childListSorted() {
        return this.chr.findAllByChildOrderBySurname();
    }

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
    public String viewUser(Model model, @ModelAttribute User user) {
        return "admin/view_user";
    }

    @GetMapping("/user/email/{userId}")
    public String userSendEmail(@PathVariable long userId, Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("title",
                this.ur.findOne(userId).getUserDetails().getName() + " " +
                        this.ur.findOne(userId).getUserDetails().getSurname());
        return "message/adminToUser";
    }

    @PostMapping("/user/email/{userId}")
    public String userSendEmailPost(@Valid Message message,
                                    BindingResult result,
                                    Model model,
                                    @PathVariable long userId,
                                    @ModelAttribute User user,
                                    Authentication auth,
                                    ServletRequest servletRequest) throws EmailException, AddressException {
        if (result.hasErrors()) {
            return "message/adminToUser";
        }

        this.mr.save(message);
        ArrayList<String> emailList = new ArrayList<>();
        emailList.add(this.ur.findOne(userId).getEmail());
        EmailMessage emailMessage = new EmailMessage(servletRequest, emailList, message);
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

    @GetMapping("/group/{id}/message")
    public String groupsMessage(Model model, @PathVariable long id) {
        model.addAttribute("message", new Message());
        model.addAttribute("groupSelected", id);
//        model.addAttribute("groupTo", new ArrayList<String>());
        return "message/adminToGroup";
    }

    @PostMapping("/group/{id}/message")
    public String groupsMessagePost(@Valid Message message, BindingResult result,
                                    @PathVariable long id, Model model,
                                    @ModelAttribute User user,
                                    @RequestParam List<String> groupTo,
                                    ServletRequest servletRequest) throws EmailException, AddressException {

        groupTo.remove("0");
        if (result.hasErrors() || groupTo.size() == 0) {
            model.addAttribute("groupSelected", id);
            return "message/adminToGroup";
        }

        ArrayList<String> emailList = new ArrayList<>();
        for (String groupId : groupTo) {
            for (String email : this.chr.getUserEmailByChildGroup(groupId)) {
                if (!emailList.contains(email)) emailList.add(email);
            }
        }

        this.mr.save(message);
        EmailMessage emailMessage = new EmailMessage(servletRequest, emailList, message);
        return "redirect:/admin/group";

    }


    @GetMapping("/user/delete/{userId}")
    @Transactional
    public String removeUser(@PathVariable long userId,
                             Model model) {

        for (Child child : this.chr.getAllChildsByUser(userId)) {
            Long childId = child.getId();
            child.setChildGroup(null);
            this.chr.delete(childId);
        }

        this.ur.delete(userId);
        return "redirect:/admin/user";
    }


}
