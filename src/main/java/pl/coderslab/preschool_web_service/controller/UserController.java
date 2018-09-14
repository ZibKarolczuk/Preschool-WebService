package pl.coderslab.preschool_web_service.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.preschool_web_service.entity.*;
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
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"user", "userDetails"})
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

    @ModelAttribute("userDetails")
    public UserDetails userDetailsAttribute(Authentication auth) {
        UserDetails userDetails = this.udr.findOne(this.ur.findByUsername(auth.getName()).getId());
        return userDetails;
    }

    @ModelAttribute("groups")
    public List<ChildGroup> childGroupList() {
        return this.cgr.findAll();
    }

    @GetMapping("")
    public String userHome(Model model, @ModelAttribute User user, @ModelAttribute UserDetails userDetails) {
        model.addAttribute("childs", this.chr.findAllByUserDetails(userDetails));
        model.addAttribute("userDetails", userDetails);
        return "user/main";
    }

//    @PostMapping("")
//    public String userHomePost(Model model, @RequestParam long childId, @RequestParam long groupId) {
//        Child child = this.chr.findOne(childId);
//        child.setChildGroup(this.cgr.findOne(groupId));
//        this.chr.save(child);
//        return "redirect:/user";
//    }

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
                              @ModelAttribute UserDetails userDetails,
                              ServletRequest servletRequest) throws EmailException, AddressException {
        if (result.hasErrors()) {
            return "message/userToAdmin";
        }

        LocalDateTime timeStamp = LocalDateTime.now();

        ArrayList<String> emailList = new ArrayList<>();
        emailList.add(servletRequest.getServletContext().getInitParameter("emailFrom"));

        message.setSendFrom(user.getEmail());
        message.setSendTo(Arrays.asList("admin").toString());
        message.setDateTime(timeStamp.toString());
        this.mr.save(message);

        String messageFromDescription = "\"" + userDetails.getName() + " " + userDetails.getSurname() + "\"";
        String messageBody = "Wiadomość od: " + userDetails.getName() + " " + userDetails.getSurname() + "\n" +
                "Z adresu e-mail: " + user.getEmail() + "\n" +
                "Wysłano dnia: " + timeStamp.toString() +  "\n\nTreść wiadomości:\n\n" +
                message.getMessage();
        String messageTitle = "FROM: " + user.getEmail() + ", TITLE: " + message.getTitle();

        message.setTitle(messageTitle);
        message.setMessage(messageBody);
        message.getUserDetails().add(userDetails);

        EmailMessage emailMessage = new EmailMessage(servletRequest, emailList, messageFromDescription, message);

        return "redirect:/user";
    }


    @GetMapping("/textSMS")
    public String textSMS(Model model, @ModelAttribute User user) {
        model.addAttribute("textSMS", new TextSMS());
        return "message/textToAdmin";
    }

    @PostMapping("/textSMS")
    public String textSMSPost(){
        return "user/smsNotDelivered";
    }

    @GetMapping("/update")
    public String updateInformation(Model model, @ModelAttribute UserDetails userDetails) {
        model.addAttribute("userDetails", userDetails);
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
//    @ResponseBody
    public String addChildPost(@Valid Child child,
                               BindingResult result,
                               Authentication auth) {
        if (result.hasErrors()) {
            return "form/new_child";
        }
//        return "BIRTHDAY " + child.getBirthday();
        child.setUserDetails(this.udr.findOne(this.ur.findByUsername(auth.getName()).getId()));
        this.chr.save(child);
        return "redirect:/user";
    }

    @GetMapping("/child/edit/{childId}")
    public String editChild(Model model, @PathVariable BigInteger childId,
                            @ModelAttribute User user,
                            @ModelAttribute UserDetails userDetails) {

        boolean check = false;
        for (BigInteger s : this.chr.getAllChildIdByAuthorisedUser(userDetails.getId())) {
            if (s.equals(childId)) {
                check = true;
            }
        }
        if (!check) return "error/notAuthorizedChild";
        model.addAttribute("child", this.chr.getChildWithAuthorisedUser(childId, userDetails.getId()));
        model.addAttribute("title", "Edytuj informacje o dziecku w bazie danych");
        return "form/new_child";
    }

    @PostMapping("/child/edit/{childId}")
    public String edditChildPost(@Valid Child child,
                                 BindingResult result,
                                 Authentication auth,
                                 @PathVariable Long childId) {
        if (result.hasErrors()) {
            return "form/new_child";
        }
        child.setUserDetails(this.udr.findOne(this.ur.findByUsername(auth.getName()).getId()));
        child.setChildGroup(this.chr.getChildById(childId).getChildGroup());
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
