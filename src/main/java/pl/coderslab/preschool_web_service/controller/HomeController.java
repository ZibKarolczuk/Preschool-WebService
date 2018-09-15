package pl.coderslab.preschool_web_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.preschool_web_service.entity.UserDetails;
import pl.coderslab.preschool_web_service.entity.security.User;
import pl.coderslab.preschool_web_service.repository.UserDetailsRepository;
import pl.coderslab.preschool_web_service.repository.security.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private  UserRepository ur;

    @Autowired
    private UserDetailsRepository udr;

    @GetMapping("")
    public String dispatchUser(Model model, Authentication auth) {
        UserDetails userDetails = this.udr.findOne(this.ur.findByUsername(auth.getName()).getId());

        if (auth.getName().equals("administrator")){
            model.addAttribute("userDetails", userDetails);
            return "redirect:admin";
        } else if (userDetails.getName() != null || userDetails.getSurname() != null) {
            model.addAttribute("userDetails", userDetails);
            return "redirect:user";
        } else {
            return "redirect:user/update";
        }

    }

}
