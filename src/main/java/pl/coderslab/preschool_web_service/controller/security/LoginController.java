package pl.coderslab.preschool_web_service.controller.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.preschool_web_service.entity.Message;
import pl.coderslab.preschool_web_service.model.security.UserDto;
import pl.coderslab.preschool_web_service.repository.MessageRepository;
import pl.coderslab.preschool_web_service.service.security.IUserService;
import pl.coderslab.preschool_web_service.validation.security.EmailExistsException;


@Controller
public class LoginController {
	@Autowired
	IUserService userService;

	@Autowired
	MessageRepository mr;

	@GetMapping("/login")
	public ModelAndView login() {
//		Message msg = new Message();
//		msg.setTitle("ąęółć");
//		mr.save(msg);
		ModelAndView model = new ModelAndView();
		model.setViewName("security/login");
		return model;
	}

	@GetMapping("register")
	public String register(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "security/register";
	}

	@PostMapping("register")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto, BindingResult result,
			WebRequest request, Errors errors, RedirectAttributes redirectAttributes) {
		pl.coderslab.preschool_web_service.entity.security.User registered = new pl.coderslab.preschool_web_service.entity.security.User();
		if (!result.hasErrors()) {
			registered = createUserAccount(accountDto, result);
		}
		if (registered == null) {
			result.rejectValue("email", "message.regError");
		}
		if (result.hasErrors()) {
			return "security/register";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Registration successful!");
			return "redirect:/";
		}
	}

	private pl.coderslab.preschool_web_service.entity.security.User createUserAccount(UserDto accountDto, BindingResult result) {
		pl.coderslab.preschool_web_service.entity.security.User registered = null;
		try {
			registered = userService.registerNewUserAccount(accountDto);
		} catch (EmailExistsException e) {
			return null;
		}
		return registered;
	}
}
