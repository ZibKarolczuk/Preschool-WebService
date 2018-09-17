package pl.coderslab.preschool_web_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.preschool_web_service.entity.UserDetails;
import pl.coderslab.preschool_web_service.entity.security.User;
import pl.coderslab.preschool_web_service.repository.UserDetailsRepository;
import pl.coderslab.preschool_web_service.repository.security.UserRepository;

import javax.lang.model.element.Element;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/")
@SessionAttributes({"user", "userDetails"})
public class HomeController {

    @Autowired
    private  UserRepository ur;

    @Autowired
    private UserDetailsRepository udr;

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

    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    @GetMapping("")
    public String dispatchUser(Model model, @ModelAttribute UserDetails userDetails, @ModelAttribute User user, Authentication auth) {

        if (hasRole("ROLE_ADMIN")){
            model.addAttribute("userDetails", userDetails);
            return "redirect:admin";
        } else if (hasRole("ROLE_USER") && (userDetails.getName() == null || userDetails.getSurname() == null)){
            model.addAttribute("userDetails", userDetails);
            return "redirect:user/update";
        } else if (hasRole("ROLE_USER")) {
            model.addAttribute("userDetails", userDetails);
            return "redirect:user";
        } else {
            return "redirect:/accessDenied";
        }

    }

    @GetMapping("accessDenied")
    public String accessDenied(){
        return "error/notAuthorized";
    }

}
