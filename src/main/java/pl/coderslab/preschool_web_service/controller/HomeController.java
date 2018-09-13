package pl.coderslab.preschool_web_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.preschool_web_service.repository.security.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String dispatchUser(){
            return "redirect:user";

    }

}
