package pl.coderslab.preschool_web_service.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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
import javax.servlet.ServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserRepository ur;

    @Autowired
    UserDetailsRepository udr;

    @Autowired
    private ChildRepository chr;

    @Autowired
    private ChildGroupRepository cgr;

    @Autowired
    private MessageRepository mr;

    @ModelAttribute("user")
    public User userAttribute(Authentication auth) {
        User user = this.ur.findByUsername(auth.getName());
        return user;
    }

    @ModelAttribute("groups")
    public List<ChildGroup> childGroupList() {
        return this.cgr.findAll();
    }

    @GetMapping("")
    public String userHome(Model model, @ModelAttribute User user) {
        model.addAttribute("childs", this.chr.findAllByUserDetails(user.getUserDetails()));
        return "user/main";
    }

    @PostMapping("")
    public String userHomePost(Model model, @RequestParam long childId, @RequestParam long groupId) {
        Child child = this.chr.findOne(childId);
        child.setChildGroup(this.cgr.findOne(groupId));
        this.chr.save(child);
        return "redirect:/user";
    }

    @GetMapping("/message")
    public String message(Model model, @ModelAttribute User user) {
        model.addAttribute("message", new Message());
        return "message/userToAdmin";
    }

    @PostMapping("/message")
    public String messagePost(@Valid Message message,
                              BindingResult result,
                              Model model,
                              @ModelAttribute User user,
                              ServletRequest servletRequest) throws EmailException, AddressException {
        if (result.hasErrors()) {
            return "message/userToAdmin";
        }
        ArrayList<String> emailList = new ArrayList<>();
        emailList.add(servletRequest.getServletContext().getInitParameter("emailFrom"));
        EmailMessage emailMessage = new EmailMessage(servletRequest, emailList, message);
        this.mr.save(message);
        return "redirect:/user";
    }

    @GetMapping("/update")
    public String updateInformation(Model model, @ModelAttribute User user) {
        model.addAttribute("userdetails", user.getUserDetails());
        return "form/update_userdetails";
    }

    @PostMapping("/update")
    public String updateInformationPost(@Valid UserDetails userDetails,
                                        BindingResult result,
                                        Model model, Authentication auth) {
        if (result.hasErrors()) {
            return "form/update_userdetails";
        }
        this.udr.save(userDetails);
        model.addAttribute("user", this.ur.findByUsername(auth.getName()));
        return "redirect:/user";
    }

    @GetMapping("/child/add")
    public String addChild(Model model) {
        model.addAttribute("child", new Child());
        model.addAttribute("title", "Dodaj nowe dziecko do bazy danych");
        return "form/new_child";
    }

    @PostMapping("/child/add")
    public String addChildPost(@Valid Child child,
                               BindingResult result,
                               Model model, @ModelAttribute User user) {
        if (result.hasErrors()) {
            return "form/new_child";
        }
        child.setUserDetails(user.getUserDetails());
        this.chr.save(child);
        return "redirect:/user";
    }

    @GetMapping("/child/edit/{childId}")
    public String editChild(Model model, @PathVariable BigInteger childId,
                            @ModelAttribute User user) {

        boolean check = false;
        for (BigInteger s : this.chr.getAllChildIdByAuthorisedUser(user.getUserDetails().getId())) {
            if (s.equals(childId)) {
                check = true;
            }
        }
        if (!check) return "error/notAuthorizedChild";

        model.addAttribute("child", this.chr.getChildWithAuthorisedUser(childId, user.getUserDetails().getId()));
        model.addAttribute("title", "Edytuj informacje o dziecku w bazie danych");
        return "form/new_child";

    }

    @PostMapping("/child/edit/{id}")
    public String edditChildPost(@Valid Child child,
                                 BindingResult result,
                                 Model model, @ModelAttribute User user,
                                 @PathVariable long id) {
        if (result.hasErrors()) {
            return "form/new_child";
        }
        child.setUserDetails(user.getUserDetails());
        this.chr.save(child);
        return "redirect:/user";
    }

    @GetMapping("/child/delete/{id}")
    @Transactional
    public String removeChild(Model model, @ModelAttribute User user,
                              @PathVariable long id) {
        this.chr.findOne(id).setChildGroup(null);
        this.chr.findOne(id).setUserDetails(null);
        this.chr.delete(id);
        return "redirect:/user";
    }

}
